    package com.example.demo.controller;

    import com.example.demo.DTO.ToClient.*;
    import com.example.demo.DTO.ToServer.*;
    import com.example.demo.model.DataBase.User;
    import com.example.demo.service.LoginService;
    import com.example.demo.service.SignupService;
    import com.example.demo.service.SignupRemoveService;
    import com.example.demo.service.LogQueryService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.messaging.handler.annotation.DestinationVariable;
    import org.springframework.messaging.handler.annotation.MessageMapping;
    import org.springframework.messaging.handler.annotation.SendTo;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.PathVariable;

    import java.util.List;


    @RestController
    public class UserGetPostController {
        @Autowired
        private final LoginService loginService; // AdminLoginService 주입
        @Autowired
        private final SignupService signupService;
        private final SignupRemoveService signupRemoveService;
        @Autowired
        private final LogQueryService logQueryService;


        public UserGetPostController(LoginService loginService, SignupService signupService,
                                     SignupRemoveService signupRemoveService, LogQueryService logQueryService) {
            this.loginService = loginService;
            this.signupService = signupService;
            this.signupRemoveService = signupRemoveService;
            this.logQueryService = logQueryService;
        }

        @PostMapping("/api/user/login")
        public LoginResponse login(@RequestBody UserLoginRequest request) {
            // 전화번호와 비밀번호를 받아 서비스를 통해 인증
            System.out.println(request.getUserPhoneNumber() + request.getUserPassword());
            User isValidUser = loginService.validateUserCredentials(request.getUserPhoneNumber(), request.getUserPassword());
            if (isValidUser != null) {
                // 인증된 사용자면 success 반환
                return new LoginResponse("200", isValidUser.getName(), 0);
            } else {
                // 인증 실패 시
                return new LoginResponse("601", null, 0);
            }
        }

        @PostMapping("/api/user/signup/generate-verification-code")
        public StatusResponse generateVerificationCode(@RequestBody UserPhoneNumberRequest request) {
            String userPhoneNumber = request.getUserPhoneNumber();
            String generatedVerificationCode = signupService.sendVerificationSMS(userPhoneNumber);

            if (generatedVerificationCode != null) {
                return new StatusResponse("200");
            } else {
                return new StatusResponse("701");
            }
        }

        @PostMapping("/api/user/signup/complete")
        public StatusResponse completeSignup(@RequestBody UserSignupRequest request) {
            String userPhoneNumber = request.getPhoneNumber();
            String userVerificationCode = request.getVerificationCode();
            String userPassword = request.getPassword();
            String userName = request.getUsername();
            System.out.println(userPhoneNumber);

            boolean isRegistered = signupService.registerUser(userPhoneNumber, userVerificationCode, userPassword, userName);
            if (isRegistered) {
                return new StatusResponse("200");
            } else {
                return new StatusResponse("702");
            }
        }

        @PostMapping("/api/user/signup/remove")
        public StatusResponse removeSignup(@RequestBody UserSignupRemoveRequest request) {
            boolean isRemoved = signupRemoveService.removeSignup(request);
            if (isRemoved) {
                return new StatusResponse("200");
            } else {
                return new StatusResponse("801");
            }
        }

        @PostMapping("/api/user/signup/find/generate-verification-code")
        public StatusResponse findSignupPassword(@RequestBody UserPhoneNumberRequest request) {
            String userPhoneNumber = request.getUserPhoneNumber();
            String generatedVerificationCode = signupService.sendVerificationSMSForFindPassword(userPhoneNumber);

            if (generatedVerificationCode != null) {
                return new StatusResponse("200");
            } else {
                return new StatusResponse("703");
            }
        }
        @PostMapping("/api/user/signup/find/reset-password")
        public StatusResponse resetpassword(@RequestBody UserSignupRequest request) {
            String userPhoneNumber = request.getPhoneNumber();
            String userVerificationCode = request.getVerificationCode();
            String userPassword = request.getPassword();
            System.out.println(userPhoneNumber);

            boolean resetsucess = signupService.resetUser(userPhoneNumber, userVerificationCode, userPassword);
            if (resetsucess) {
                return new StatusResponse("200");
            } else {
                return new StatusResponse("704");
            }
        }

        @PostMapping("/api/user/log")
        public LogResponse handleAdminLog(@RequestBody UserLogRequest request){
            LogResponse logResponse = logQueryService.getUserLogs(request.getUserPhoneNumber());
            return logResponse;
        }

    }
