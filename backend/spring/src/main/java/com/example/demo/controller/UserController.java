package com.example.demo.controller;

import com.example.demo.DTO.ToClient.LoginResponse;
import com.example.demo.DTO.ToServer.UserLoginRequest;
import com.example.demo.DTO.ToServer.UserSignupRequest;
import com.example.demo.model.DataBase.User;
import com.example.demo.service.LoginService;
import com.example.demo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {
    @Autowired
    private final LoginService loginService; // AdminLoginService 주입
    private final SignupService signupService;

    public UserController(LoginService loginService, SignupService signupService) {
        this.loginService = loginService;
        this.signupService = signupService;
    }

    @MessageMapping("/user/login/{userPhoneNumber}")
    @SendTo("/topic/user/login/{userPhoneNumber}")
    public LoginResponse login(UserLoginRequest request, @DestinationVariable String userPhoneNumber) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        User isValidUser = loginService.validateUserCredentials(request.getUserPhoneNumber(), request.getUserPassword());
        if (isValidUser != null) {
            // 인증된 사용자면 success 반환
            return new LoginResponse("success", null, 0);
        } else {
            // 인증 실패 시
            return new LoginResponse("failure", null, 0);
        }
    }
    // 전화번호로 인증번호를 생성하는 핸들러
    @MessageMapping("/user/signup/generate-verification-code/{userPhoneNumber}")
    @SendTo("/topic/user/signup/generate-verification-code/{userPhoneNumber}")
    public LoginResponse generateVerificationCode(@DestinationVariable String userPhoneNumber) {
        String generatedVerificationCode = signupService.sendVerificationSMS(userPhoneNumber);

        if (generatedVerificationCode != null) {
            return new LoginResponse("success", "인증번호가 성공적으로 생성되었습니다.", 0);
        } else {
            return new LoginResponse("failure", "인증번호 생성에 실패했습니다.", 0);
        }
    }

    // 전화번호와 인증번호를 이용하여 회원가입을 완료하는 핸들러
    @MessageMapping("/user/signup/complete/{userPhoneNumber}")
    @SendTo("/topic/user/signup/complete/{userPhoneNumber}")
    public LoginResponse completeSignup(UserSignupRequest request, @DestinationVariable String userPhoneNumber) {
        String userVerificationCode = request.getVerificationCode();
        String userPassword = request.getPassword();

        boolean isValid = signupService.verifySignup(userPhoneNumber, userVerificationCode, userPassword);

        if (isValid) {
            return new LoginResponse("success", "회원가입이 성공적으로 완료되었습니다.", 0);
        } else {
            return new LoginResponse("failure", "회원가입에 실패했습니다. 인증번호나 비밀번호가 잘못되었습니다.", 0);
        }
    }

}
