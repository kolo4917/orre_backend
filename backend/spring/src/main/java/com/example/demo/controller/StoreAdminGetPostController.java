package com.example.demo.controller;

import com.example.demo.DTO.ToClient.*;
import com.example.demo.DTO.ToServer.*;
import com.example.demo.model.DataBase.Admin;
import com.example.demo.service.EmptySeatService;
import com.example.demo.service.LoginService;
import com.example.demo.service.NoShowService;
import com.example.demo.service.UserCallService;
import com.example.demo.service.JwtService;
import com.example.demo.service.TableFixService;
import com.example.demo.service.StoreMenuAvailableService;
import com.example.demo.service.StoreService;
import com.example.demo.service.StoreMenuOrderService;
import com.example.demo.service.StoreMenuOrderedCheckService;
import com.example.demo.service.StoreWaitingAvailableService;
import com.example.demo.service.StoreCategoriesService;
import com.example.demo.service.StoreEnteringService;
import com.example.demo.service.LogQueryService;
import com.example.demo.service.StoreClosingService;
import com.example.demo.service.SignupService;
import com.example.demo.service.StoreUserDeleteService;
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
    private final UserCallService userCallService;
    private final JwtService jwtService;
    private final TableFixService tableFixService;
    private final StoreMenuAvailableService storeMenuAvailableService;
    private final StoreService storeService;
    private final StoreMenuOrderService storeMenuOrderService;
    private final StoreMenuOrderedCheckService storeMenuOrderedCheckService;
    private final StoreWaitingAvailableService storeWaitingAvailableService;
    private final StoreCategoriesService storeCategoriesService;
    private final StoreEnteringService storeEnteringService;
    private final LogQueryService logQueryService;
    private final StoreClosingService storeClosingService;
    private final SignupService signupService;
    private final StoreUserDeleteService storeUserDeleteService;



    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }
    @Autowired
    public StoreAdminGetPostController(EmptySeatService emptySeatService, LoginService loginService, NoShowService noShowService,
                                       UserCallService userCallService, JwtService jwtService, TableFixService tableFixService,
                                       StoreMenuAvailableService storeMenuAvailableService, StoreService storeService,
                                       StoreMenuOrderService storeMenuOrderService, StoreMenuOrderedCheckService storeMenuOrderedCheckService ,
                                       StoreWaitingAvailableService storeWaitingAvailableService, StoreCategoriesService storeCategoriesService,
                                       StoreEnteringService storeEnteringService, LogQueryService logQueryService,
                                       StoreClosingService storeClosingService, SignupService signupService, StoreUserDeleteService storeUserDeleteService) {
        this.emptySeatService = emptySeatService;
        this.loginService = loginService;
        this.noShowService = noShowService;
        this.userCallService = userCallService;
        this.jwtService = jwtService;
        this.tableFixService = tableFixService;
        this.storeMenuAvailableService =storeMenuAvailableService;
        this.storeService = storeService;
        this.storeMenuOrderService = storeMenuOrderService;
        this.storeMenuOrderedCheckService = storeMenuOrderedCheckService;
        this.storeWaitingAvailableService = storeWaitingAvailableService;
        this.storeCategoriesService = storeCategoriesService;
        this.storeEnteringService = storeEnteringService;
        this.logQueryService = logQueryService;
        this.storeClosingService = storeClosingService;
        this.signupService = signupService;
        this.storeUserDeleteService = storeUserDeleteService;
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

    @PostMapping("/api/admin/StoreAdmin/login")
    public LoginResponse login(@RequestBody AdminLoginRequest request) {
        Admin isValidUser = loginService.validateAdminCredentials(request.getAdminPhoneNumber(), request.getAdminPassword());
        if (isValidUser != null) {
            // JWT 발급
            String token = generateJwtTokenForAdmin(request.getAdminPhoneNumber());
            String status = loginService.saveAdminFcmToken(request.getAdminPhoneNumber(),request.getAdminFcmToken());
            return new LoginResponse(status, token, isValidUser.getAdminStoreCode());
        } else {
            // 인증 실패 시
            return new LoginResponse("failure", null, 0);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/generate-verification-code")
    public StatusResponse generateVerificationCode(@RequestBody AdminPhoneNumberRequest request) {
        String adminPhoneNumber = request.getAdminPhoneNumber();
        String generatedVerificationCode = signupService.sendVerificationSMSForFindAdminPassword(adminPhoneNumber);

        if (generatedVerificationCode != null) {
            return new StatusResponse("200");
        } else {
            return new StatusResponse("701");
        }
    }
    @PostMapping("/api/admin/StoreAdmin/login/reset")
    public StatusResponse resetAdminPassword(@RequestBody AdminPasswordModifyRequest request){
            String status = signupService.resetAdmin(request.getAdminPhoneNumber(),request.getNewAdminPassword(),request.getVerificationCode());
            return new StatusResponse(status);
    }

    @PostMapping("/api/admin/StoreAdmin/storeInfo")
    public StoreDTO getStoreInfo(@RequestBody StoreInfoRequest request) {
        return storeService.getStoreDetailsByStoreCode(request.getStoreCode());
    }
    @PostMapping("/api/admin/StoreAdmin/available/waiting")
    public StatusResponse waitingAvailable(@RequestBody StoreWaitingAvailableRequest request){

        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String status = storeWaitingAvailableService.updateStoreWaitingAvailable(request);
            return new StatusResponse(status);
        } else {
            return new StatusResponse("400");
        }
    }

    @PostMapping("/api/admin/StoreAdmin/available")
    public List<EmptySeat> emptySeat(@RequestBody StoreInfoRequest request) {
        // EmptySeatService를 사용하여 storeCode에 해당하는 비어 있는 자리 정보 조회
        return emptySeatService.findEmptySeats(request.getStoreCode());
    }


    @PostMapping("/api/admin/StoreAdmin/noShow")
    public BooleanResponse handleNoShow(@RequestBody AdminNoShowRequest request) {
        return noShowService.handleNoShowCustomers(request.getNoShowUserCode(), request.getStoreCode());
    }
    @PostMapping("/api/admin/StoreAdmin/userDelete")
    public BooleanResponse handleUserDelete(@RequestBody AdminDeleteUserRequest request) {
        return storeUserDeleteService.handleDeleteUser(request.getDeleteUserCode(), request.getStoreCode());
    }
    @PostMapping("/api/admin/StoreAdmin/userCall")
    public UserCallResponse userCall(@RequestBody UserCallRequest request) {
        return userCallService.callUser(request);
    }

    @PostMapping("/api/admin/StoreAdmin/table/add")
    public BooleanResponse handleTableAdd(@RequestBody TableAddRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if(isValidAdmin){
            return tableFixService.addTable(request);
        }
        else {
            return new BooleanResponse(false);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/table/remove")
    public BooleanResponse handleTableRemove(@RequestBody TableRemoveRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if(isValidAdmin){
            return tableFixService.removeTable(request);
        }
        else {
            return new BooleanResponse(false);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/menu/order/available")
    public BooleanResponse handleOrderAvailable(@RequestBody StoreMenuAvailableRequest request) {
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            storeMenuAvailableService.updateMenuAvailability(request);
            return new BooleanResponse(true);
        } else {
            return new BooleanResponse(false);
        }
    }

    @PostMapping("/api/admin/StoreAdmin/menu/order/amount")
    public BooleanResponse handleMenuAdd(@RequestBody StoreMenuOrderRequest request){
        String jwtAdmin = request.getJwt();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            storeMenuOrderService.ModifyMenuAmount(request);
            return new BooleanResponse(true);
        } else {
            return new BooleanResponse(false);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/menu/order/check")
    public StoreMenuOrderedCheck handleMenuCheck(@RequestBody StoreMenuOrderedCheckRequest request){
        try {
            return storeMenuOrderedCheckService.getStoreMenuOrderedCheck(request);
        } catch (Exception e) {
            return new StoreMenuOrderedCheck("1001",-1,-1,-1, null);
        }
    }
    @PostMapping("/api/admin/StoreAdmin/menu/category/modify")
    public StatusResponse handleCategoryAdd(@RequestBody StoreMenuCategoryRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String status = storeCategoriesService.updateMenuCategory(request);
            return new StatusResponse(status);
        } else {
            return new StatusResponse("400");
        }
    }
    @PostMapping("/api/admin/StoreAdmin/entering")
    public StatusResponse handleEntering(@RequestBody StoreEnterRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String status = storeEnteringService.handleEnteringService(request);
            return new StatusResponse(status);
        } else {
            return new StatusResponse("400");
        }
    }
    @PostMapping("/api/admin/StoreAdmin/closing")
    public StatusResponse handleClosing(@RequestBody AdminClosingRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String status = storeClosingService.closeStore(request.getStoreCode());
            return new StatusResponse(status);
        } else {
            return new StatusResponse("400");
        }
    }

    @PostMapping("/api/admin/StoreAdmin/log")
    public LogResponse handleAdminLog(@RequestBody AdminLogRequest request){
        String jwtAdmin = request.getJwtAdmin();
        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            LogResponse logResponse = logQueryService.getStoreLogs(request.getStoreCode());
            return logResponse;
        } else {
            return new LogResponse(null,"400");
        }
    }



}
