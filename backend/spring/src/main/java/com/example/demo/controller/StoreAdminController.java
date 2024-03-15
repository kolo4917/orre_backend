package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.example.demo.DTO.ToClient.AdminLoginResponse;
import com.example.demo.DTO.ToServer.AdminLoginRequest;
import com.example.demo.model.DataBase.Admin;
import com.example.demo.service.AdminLoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Controller
public class StoreAdminController {
    @Autowired
    private AdminLoginService adminLoginService; // AdminLoginService 주입

    @MessageMapping("/admin/StoreAdmin/login")
    @SendTo("/topic/admin/StoreAdmin/login")
    public AdminLoginResponse login(AdminLoginRequest request) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        Admin isValidUser = adminLoginService.validateAdminCredentials(request.getAdminPhoneNumber(), request.getAdminPassword());

        if (isValidUser != null) {
            // JWT 발급
            String token = generateJwtToken(request.getAdminPhoneNumber());
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
}
