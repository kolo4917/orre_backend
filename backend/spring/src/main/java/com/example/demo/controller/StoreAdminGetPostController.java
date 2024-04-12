package com.example.demo.controller;

import com.example.demo.DTO.ToClient.EmptySeat;
import com.example.demo.DTO.ToClient.LoginResponse;
import com.example.demo.DTO.ToClient.BooleanResponse;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.DTO.ToServer.AdminLoginRequest;
import com.example.demo.DTO.ToServer.AdminNoShowRequest;
import com.example.demo.model.DataBase.Admin;
import com.example.demo.service.EmptySeatService;
import com.example.demo.service.LoginService;
import com.example.demo.service.NoShowService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@RestController
public class StoreAdminGetPostController {

    private SecretKey SECRET_KEY;
    @Value("${jwt.secret}")
    private String secret;
    @Autowired
    private final EmptySeatService emptySeatService;
    private final LoginService loginService;
    private final NoShowService noShowService;


    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }
    @Autowired
    public StoreAdminGetPostController(EmptySeatService emptySeatService, LoginService loginService, NoShowService noShowService) {
        this.emptySeatService = emptySeatService;
        this.loginService = loginService;
        this.noShowService = noShowService;
    }

    private String generateJwtTokenForAdmin(String adminPhoneNumber) {
        long expirationTime = 86400000; // 24시간의 밀리초
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(adminPhoneNumber) // 관리자 전화번호를 주체로 설정
                .claim("role", "admin") // 역할을 'admin'으로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    // 생성자를 통한 EmptySeatService 의존성 주입

    @PostMapping("/api/admin/StoreAdmin/available")
    public List<EmptySeat> emptySeat(@RequestBody StoreInfoRequest request) {
        // EmptySeatService를 사용하여 storeCode에 해당하는 비어 있는 자리 정보 조회
        return emptySeatService.findEmptySeats(request.getStoreCode());
    }

    @PostMapping("/api/admin/StoreAdmin/login")
    public LoginResponse login(@RequestBody AdminLoginRequest request) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        Admin isValidUser = loginService.validateAdminCredentials(request.getAdminPhoneNumber(), request.getAdminPassword());
        if (isValidUser != null) {
            // JWT 발급
            String token = generateJwtTokenForAdmin(request.getAdminPhoneNumber());
            // 인증된 사용자의 storeCode를 가져오는 로직 (가정)
            return new LoginResponse("success", token, isValidUser.getAdminStoreCode());
        } else {
            // 인증 실패 시
            return new LoginResponse("failure", null, 0);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/noShow")
    public BooleanResponse handleNoShow(@RequestBody AdminNoShowRequest request) {
        // NoShowService를 사용하여 no-show 처리
        return noShowService.handleNoShowCustomers(request.getNoShowUserCode(), request.getStoreCode());
    }
}
