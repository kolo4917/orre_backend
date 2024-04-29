    package com.example.demo.controller;

    import com.example.demo.DTO.ToClient.EmptySeat;
    import com.example.demo.DTO.ToClient.LoginResponse;
    import com.example.demo.DTO.ToClient.StoreDTO;
    import com.example.demo.DTO.ToServer.StoreInfoRequest;
    import com.example.demo.DTO.ToServer.UserLoginRequest;
    import com.example.demo.DTO.ToServer.UserSignupRequest;
    import com.example.demo.DTO.ToServer.UserPhoneNumberRequest;
    import com.example.demo.model.DataBase.User;
    import com.example.demo.service.EmptySeatService;
    import com.example.demo.service.LoginService;
    import com.example.demo.service.SignupService;
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

        public UserGetPostController(LoginService loginService, SignupService signupService) {
            this.loginService = loginService;
            this.signupService = signupService;
        }

        @PostMapping("/api/user/login")
        public LoginResponse login(@RequestBody UserLoginRequest request) {
            // 전화번호와 비밀번호를 받아 서비스를 통해 인증
            System.out.println(request.getUserPhoneNumber() + request.getUserPassword());
            User isValidUser = loginService.validateUserCredentials(request.getUserPhoneNumber(), request.getUserPassword());
            if (isValidUser != null) {
                // 인증된 사용자면 success 반환
                return new LoginResponse("success", null, 0);
            } else {
                // 인증 실패 시
                return new LoginResponse("failure", null, 0);
            }
        }

        @PostMapping("/api/user/signup/generate-verification-code")
        public LoginResponse generateVerificationCode(@RequestBody UserPhoneNumberRequest request) {
            String userPhoneNumber = request.getUserPhoneNumber();
            String generatedVerificationCode = signupService.sendVerificationSMS(userPhoneNumber);

            if (generatedVerificationCode != null) {
                return new LoginResponse("success", "인증번호가 성공적으로 생성되었습니다.", 0);
            } else {
                return new LoginResponse("failure", "인증번호 생성에 실패했습니다.", 0);
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
                return new LoginResponse("success", "회원가입이 성공적으로 완료되었습니다.", 0);
            } else {
                return new LoginResponse("failure", "회원가입에 실패했습니다. 인증번호나 비밀번호가 잘못되었습니다.", 0);
            }
        }
    }
