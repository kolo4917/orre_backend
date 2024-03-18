package com.example.demo.controller;

import com.example.demo.DTO.ToClient.LoginResponse;
import com.example.demo.DTO.ToServer.UserLoginRequest;
import com.example.demo.model.DataBase.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {
    @Autowired
    private final LoginService loginService; // AdminLoginService 주입
    public UserController(LoginService loginService) {
        this.loginService = loginService;
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
}
