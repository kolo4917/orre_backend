package com.example.demo.controller;

import com.example.demo.DTO.ToClient.*;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.DTO.ToServer.AdminLoginRequest;
import com.example.demo.DTO.ToServer.AdminNoShowRequest;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.DTO.ToServer.TableAddRequest;
import com.example.demo.DTO.ToServer.TableRemoveRequest;
import com.example.demo.DTO.ToServer.StoreMenuAvailableRequest;
import com.example.demo.DTO.ToServer.StoreMenuOrderRequest;
import com.example.demo.DTO.ToServer.StoreMenuOrderedCheckRequest;
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



    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }
    @Autowired
    public StoreAdminGetPostController(EmptySeatService emptySeatService, LoginService loginService, NoShowService noShowService,
                                       UserCallService userCallService, JwtService jwtService, TableFixService tableFixService,
                                       StoreMenuAvailableService storeMenuAvailableService, StoreService storeService,
                                       StoreMenuOrderService storeMenuOrderService, StoreMenuOrderedCheckService storeMenuOrderedCheckService) {
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
    @PostMapping("/api/admin/storeInfo")
    public StoreDTO getStoreInfo(@RequestBody StoreInfoRequest request) {
        return storeService.getStoreDetailsByStoreCode(request.getStoreCode());
    }
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
            // 인증된 사용자의 storeCode를 가져오는 로직
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

}
