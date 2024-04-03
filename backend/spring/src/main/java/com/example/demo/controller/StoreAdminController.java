package com.example.demo.controller;

import com.example.demo.DTO.ToClient.EmptySeat;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToClient.BooleanResponse;
import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.DTO.ToServer.AdminNoShowRequest;
import com.example.demo.DTO.ToClient.TableUnlockResponse;
import com.example.demo.DTO.ToServer.TableUnlockRequest;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.example.demo.DTO.ToClient.LoginResponse;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueue;
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
    private final LoginService loginService; // AdminLoginService 주입
    private final StoreDynamicQueueService storeDynamicQueueService;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserCallService userCallService;
    private final JwtService jwtService;

    private final NoShowService noShowService;
    private EmptySeatService emptySeatService;
    private final TableLockingService tableLockingService;

    private String generateJwtTokenForAdmin(String adminPhoneNumber) {
        long expirationTime = 86400000; // 24시간의 밀리초
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        // 보안 키 생성
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(adminPhoneNumber) // 관리자 전화번호를 주체로 설정
                .claim("role", "admin") // 역할을 'admin'으로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private String generateJwtTokenForUser(String userPhoneNumber) {
        long expirationTime = 86400000; // 24시간의 밀리초
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        // 보안 키 생성
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(userPhoneNumber) // 사용자 전화번호를 주체로 설정
                .claim("role", "user") // 역할을 'user'로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    @Autowired
    public StoreAdminController(LoginService loginService,
                                StoreDynamicQueueService storeDynamicQueueService,
                                SimpMessagingTemplate messagingTemplate,
                                EmptySeatService emptySeatService,
                                NoShowService noShowService,
                                UserCallService userCallService,
                                JwtService jwtService,
                                TableLockingService tableLockingService) {
        this.loginService = loginService;
        this.storeDynamicQueueService = storeDynamicQueueService;
        this.messagingTemplate = messagingTemplate;
        this.emptySeatService = emptySeatService;
        this.noShowService = noShowService;
        this.userCallService = userCallService;
        this.jwtService = jwtService;
        this.tableLockingService = tableLockingService;
    }

    @MessageMapping("/admin/StoreAdmin/login/{adminPhoneNumber}")
    @SendTo("/topic/admin/StoreAdmin/login/{adminPhoneNumber}")
    public LoginResponse login(AdminLoginRequest request, @DestinationVariable String adminPhoneNumber) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        Admin isValidUser = loginService.validateAdminCredentials(request.getAdminPhoneNumber(), request.getAdminPassword());
        if (isValidUser != null) {
            // JWT 발급
            String token = generateJwtTokenForAdmin(adminPhoneNumber);
            // 인증된 사용자의 storeCode를 가져오는 로직 (가정)
            return new LoginResponse("success", token, isValidUser.getAdminStoreCode());
        } else {
            // 인증 실패 시
            return new LoginResponse("failure", null, 0);
        }
    }

    // JWT 토큰 생성 로직


    @MessageMapping("/admin/dynamicStoreWaitingInfo/{storeCode}")
    @SendTo("/topic/admin/dynamicStoreWaitingInfo/{storeCode}")
    public ExtendedStoreDynamicQueue sendExtendedDynamicQueue(@DestinationVariable Integer storeCode) {
        ExtendedStoreDynamicQueue extendedDynamicQueue = storeDynamicQueueService.findStoreByExtendedQueue(storeCode);
        return extendedDynamicQueue;
    }

    @MessageMapping("/admin/StoreAdmin/available/{storeCode}")
    @SendTo("/topic/admin/StoreAdmin/available/{storeCode}")
    public List<EmptySeat> emptySeat(StoreInfoRequest request, @DestinationVariable Integer storeCode) {
        // EmptySeatService를 사용하여 storeCode에 해당하는 비어 있는 자리 정보 조회
        return emptySeatService.findEmptySeats(storeCode);
    }
    @MessageMapping("/admin/StoreAdmin/noShow/{storeCode}")
    @SendTo("/topic/admin/StoreAdmin/noShow/{storeCode}")
    public BooleanResponse handleNoShow(AdminNoShowRequest request, @DestinationVariable Integer storeCode) {
        // NoShowService를 사용하여 no-show 처리
        return noShowService.handleNoShowCustomers(request.getNoShowUserCode(), storeCode);
    }
    @MessageMapping("/admin/StoreAdmin/userCall/{storeCode}")
    @SendTo("/topic/admin/StoreAdmin/userCall/{storeCode}")
    public UserCallResponse userCall(@DestinationVariable Integer storeCode, UserCallRequest request) {
        return userCallService.callUser(request);
    }
    @MessageMapping("/admin/table/unlock/{storeCode}")
    @SendTo("/topic/admin/table/unlock/{storeCode}")
    public TableUnlockResponse unlockTable(@DestinationVariable Integer storeCode, TableUnlockRequest request) {
        // 여기에 관리자 JWT 검증 로직
        boolean isValidAdmin = jwtService.isValid(request.getJwtAdmin());

        // 테이블 언락 로직
        boolean success = tableLockingService.unlockTable(request.getStoreCode(), request.getTableNumber(), request.getWaitingNumber());

        /* 테이블 언락 성공 시, 사용자에게 JWT 발급
        String jwtUser = "";
        if(success) {
            jwtUser = jwtService.generateJwtTokenForUser(request.getUserPhoneNumber());
            // 특정 사용자에게 메시지 전송
            messagingTemplate.convertAndSend("/topic/user/table/unlock/" + storeCode + "/" + request.getUserPhoneNumber(),
                    new TableUnlockResponse(true, jwtUser, storeCode, request.getTableNumber(), request.getWaitingNumber()));
        }

        // 관리자에게 테이블 언락 결과 전송
        return new TableUnlockResponse(success, jwtUser, storeCode, request.getTableNumber(), request.getWaitingNumber());*/
        return null;
    }

}
