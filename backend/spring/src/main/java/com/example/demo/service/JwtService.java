package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

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
            // 예외 발생 시 유효하지 않음
            return false;
        }
    }
}

