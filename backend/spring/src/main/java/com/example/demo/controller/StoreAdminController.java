package com.example.demo.controller;

import com.example.demo.DTO.ToClient.*;
import com.example.demo.DTO.ToServer.*;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.model.DataBase.Admin;
import com.example.demo.service.StoreDynamicQueueService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import jakarta.annotation.PostConstruct;
import java.util.Base64;
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
    private final UserStoreMakeWaitingService userStoreMakeWaitingService;

    private SecretKey SECRET_KEY;
    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
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

    private String generateJwtTokenForUser(String userPhoneNumber) {
        long expirationTime = 86400000; // 24시간의 밀리초
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(userPhoneNumber) // 사용자 전화번호를 주체로 설정
                .claim("role", "user") // 역할을 'user'로 설정
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    private String extractJwtToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 이후의 토큰 부분을 추출하여 반환
        }
        return null;
    }


    @Autowired
    public StoreAdminController(LoginService loginService,
                                StoreDynamicQueueService storeDynamicQueueService,
                                SimpMessagingTemplate messagingTemplate,
                                EmptySeatService emptySeatService,
                                NoShowService noShowService,
                                UserCallService userCallService,
                                JwtService jwtService,
                                TableLockingService tableLockingService,
                                UserStoreMakeWaitingService userStoreMakeWaitingService) {
        this.loginService = loginService;
        this.storeDynamicQueueService = storeDynamicQueueService;
        this.messagingTemplate = messagingTemplate;
        this.emptySeatService = emptySeatService;
        this.noShowService = noShowService;
        this.userCallService = userCallService;
        this.jwtService = jwtService;
        this.tableLockingService = tableLockingService;
        this.userStoreMakeWaitingService = userStoreMakeWaitingService;

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
        String jwtAdmin = request.getJwtAdmin();

        // 여기에 관리자 JWT 검증 로직
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);

        // 테이블 언락 로직
        String phoneNumber = tableLockingService.unlockTable(request.getStoreCode(), request.getTableNumber(), request.getWaitingNumber());
        // 테이블 언락 성공 시, 사용자에게 JWT 발급
        String jwtUser = "";
        if (phoneNumber != null && isValidAdmin) {
            jwtUser = generateJwtTokenForUser(phoneNumber);
            // 특정 사용자에게 메시지 전송
            messagingTemplate.convertAndSend("/topic/user/table/unlock/" + storeCode + "/" + phoneNumber,
                    new TableUnlockResponse(true, jwtUser, storeCode, request.getTableNumber(), request.getWaitingNumber()));
        }
        // 관리자에게 테이블 언락 결과 전송
        if (jwtUser != ""){
            return new TableUnlockResponse(true, jwtUser, storeCode, request.getTableNumber(), request.getWaitingNumber());
        }
        else {
            return new TableUnlockResponse(false, jwtUser, storeCode, request.getTableNumber(), request.getWaitingNumber());
        }
    }
    @MessageMapping("/admin/table/lock/{storeCode}")
    @SendTo("/topic/admin/table/lock/{storeCode}")
    public TableLockResponse lockTable(@DestinationVariable Integer storeCode, TableLockRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);

        if(isValidAdmin){
            String lockedPhoneNumber = tableLockingService.lockTable(request.getStoreCode(),request.getTableNumber());
            boolean expireUserJWT = jwtService.revokeUserJWT(lockedPhoneNumber);
            if(expireUserJWT){
                return new TableLockResponse(true,request.getStoreCode(),request.getTableNumber());
            }
        }
        return new TableLockResponse(false,request.getStoreCode(),request.getTableNumber());
    }

    //@MessageMapping("/admin/StoreAdmin/order/{storeCode}") //구독해놓으면 별도의 요청 필요 없음
    //@@SendTo("/admin/StoreAdmin/order/{storeCode}")

    @MessageMapping("/admin/waiting/make/{storeCode}/{userPhoneNumber}")
    @SendTo("/topic/admin/waiting/make/{storeCode}/{userPhoneNumber}")
    public UserStoreWaitResponse makeWaiting(
            @DestinationVariable Integer storeCode,
            @DestinationVariable String userPhoneNumber,
            UserStoreWaitRequest request) {
        request.setStoreCode(storeCode);
        request.setUserPhoneNumber(userPhoneNumber);

        // 대기열 생성 서비스 호출
        UserStoreWait newUserStoreWait = userStoreMakeWaitingService.createUserStoreWait(request);

        // 생성 결과를 클라이언트에 전송할 응답 객체 생성
        UserStoreWaitResponse response = new UserStoreWaitResponse();
        if (newUserStoreWait != null) {
            response.setStatus("200");
            response.setToken(newUserStoreWait); // 응답에 대기열 상세 정보 포함
        }
        else {
            response.setStatus("1101");
        }
        return response;
    }
    //@MessageMapping("/admin/log/{storeCode}") - 유저 로그 변동시 알림
    //@MessageMapping("/admin/log/{storeCode}") - 유저 로그 변동시 알림


}
