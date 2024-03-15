package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.DTO.ToClient.AdminLoginResponse;
import com.example.demo.DTO.ToServer.AdminLoginRequest;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.model.DataBase.Admin;
import com.example.demo.service.StoreDynamicQueueService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

@Controller
public class StoreAdminController {

    @Autowired
    private final AdminLoginService adminLoginService; // AdminLoginService 주입
    private final StoreDynamicQueueService storeDynamicQueueService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public StoreAdminController(AdminLoginService adminLoginService, StoreDynamicQueueService storeDynamicQueueService, SimpMessagingTemplate messagingTemplate) {
        this.adminLoginService = adminLoginService;
        this.storeDynamicQueueService = storeDynamicQueueService;
        this.messagingTemplate = messagingTemplate;

    }


    @MessageMapping("/admin/StoreAdmin/login/{adminPhoneNumber}")
    @SendTo("/topic/admin/StoreAdmin/login/{adminPhoneNumber}")
    public AdminLoginResponse login(AdminLoginRequest request, @DestinationVariable String adminPhoneNumber) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        Admin isValidUser = adminLoginService.validateAdminCredentials(request.getAdminPhoneNumber(), request.getAdminPassword());
        if (isValidUser != null) {
            // JWT 발급
            String token = generateJwtToken(adminPhoneNumber);
            // 인증된 사용자의 storeCode를 가져오는 로직 (가정)
            return new AdminLoginResponse("success", token, isValidUser.getAdminStoreCode());
        } else {
            // 인증 실패 시
            return new AdminLoginResponse("failure", null, 0);
        }
    }

    // JWT 토큰 생성 로직
    private String generateJwtToken(String adminPhoneNumber) {
        long expirationTime = 86400000; // 24시간의 밀리초
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        // 보안 키 생성
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(adminPhoneNumber)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    @MessageMapping("/admin/dynamicQueue/{storeCode}")
    @SendTo("/topic/admin/dynamicQueue/{storeCode}") //기존의 클라이언트 -> 서버 구조
    public List<StoreDynamicQueue> sendDynamicQueue3(@DestinationVariable Integer storeCode) {
        List<StoreDynamicQueue> dynamicQueues = storeDynamicQueueService.findStoreDynamicQueue(storeCode);
        return dynamicQueues;
    }
    @Scheduled(fixedRate = 50000)
    public void sendDynamicQueue4() {
        System.out.println("Sending admin dynamic queue to all stores...");
        // 정의된 스토어 코드 리스트
        List<Integer> storeCodes = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        for (Integer storeCode : storeCodes) {
            List<StoreDynamicQueue> dynamicQueues = storeDynamicQueueService.findStoreDynamicQueue(storeCode);
            String destination = "/topic/admin/dynamicQueue/" + storeCode;
            messagingTemplate.convertAndSend(destination, dynamicQueues);
        }
    }
}
