    package com.example.demo.controller;

    import com.example.demo.DTO.ToClient.BooleanResponse;
    import com.example.demo.DTO.ToClient.EmptySeat;
    import com.example.demo.DTO.ToClient.LoginResponse;
    import com.example.demo.DTO.ToClient.StoreDTO;
    import com.example.demo.DTO.ToServer.*;
    import com.example.demo.model.DataBase.User;
    import com.example.demo.service.EmptySeatService;
    import com.example.demo.service.LoginService;
    import com.example.demo.service.SignupService;
    import com.example.demo.service.SignupRemoveService;
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


        public UserGetPostController(LoginService loginService, SignupService signupService, SignupRemoveService signupRemoveService) {
            this.loginService = loginService;
            this.signupService = signupService;
            this.signupRemoveService = signupRemoveService;
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
        public LoginResponse generateVerificationCode(@RequestBody UserPhoneNumberRequest request) {
            String userPhoneNumber = request.getUserPhoneNumber();
            String generatedVerificationCode = signupService.sendVerificationSMS(userPhoneNumber);

            if (generatedVerificationCode != null) {
                return new LoginResponse("200", null, 0);
            } else {
                return new LoginResponse("701", null, 0);
            }
        }

        @PostMapping("/api/user/signup/complete")
        public LoginResponse completeSignup(@RequestBody UserSignupRequest request) {
            String userPhoneNumber = request.getPhoneNumber();
            String userVerificationCode = request.getVerificationCode();
            String userPassword = request.getPassword();
            String userName = request.getUsername();
            System.out.println(userPhoneNumber);

            boolean isRegistered = signupService.registerUser(userPhoneNumber, userVerificationCode, userPassword, userName);
            if (isRegistered) {
                return new LoginResponse("200", null, 0);
            } else {
                return new LoginResponse("702", null, 0);
            }
        }
        @PostMapping("/api/user/signup/remove")
        public LoginResponse removeSignup(@RequestBody UserSignupRemoveRequest request) {
            boolean isRemoved = signupRemoveService.removeSignup(request);
            if (isRemoved) {
                return new LoginResponse("200", null, 0);
            } else {
                return new LoginResponse("801", null, 0);
            }        }

    }
