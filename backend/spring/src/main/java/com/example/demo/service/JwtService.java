package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private SecretKey SECRET_KEY;

    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isValid(String jwtToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // JWT 내의 "role" 클레임을 확인하여 값이 "admin"인지 확인
            String role = (String) claims.get("role");
            if (role != null && role.equals("admin")) {
                // "role"이 "admin"인 경우에만 유효하도록 반환
                return true;
            }

            // "role"이 "admin"이 아닌 경우 유효하지 않음
            return false;
        } catch (Exception e) {
            // 예외 발생 시 로그를 출력
            logger.error("JWT validation error", e);
            return false;
        }
    }
    public boolean isValidUser(String jwtToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            // JWT 내의 "role" 클레임을 확인하여 값이 "user"인지 확인
            String role = (String) claims.get("role");
            if (role != null && role.equals("user")) {
                // "role"이 "admin"인 경우에만 유효하도록 반환
                return true;
            }

            // "role"이 "user"이 아닌 경우 유효하지 않음
            return false;
        } catch (Exception e) {
            // 예외 발생 시 로그를 출력
            logger.error("JWT validation error", e);
            return false;
        }
    }
    public boolean revokeUserJWT(String phoneNumber) {
        try {
            Date now = new Date();
            String revokedToken = Jwts.builder()
                    .setSubject(phoneNumber) // 사용자 전화번호를 주체로 설정
                    .claim("role", "user")
                    .setIssuedAt(now)
                    .setExpiration(now) // 현재 시간으로 설정하여 토큰을 즉시 만료시킵니다.
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                    .compact();


            return true; // JWT 폐기가 성공한 경우
        } catch (Exception e) {
            logger.error("Error revoking user JWT", e);
            return false; // JWT 폐기가 실패한 경우
        }
    }
}
