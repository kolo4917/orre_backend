package com.example.demo.controller;

import com.example.demo.DTO.ToClient.EmptySeat;
import com.example.demo.DTO.ToClient.LoginResponse;
import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.DTO.ToServer.StoreInfoRequest;
import com.example.demo.DTO.ToServer.UserLoginRequest;
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
    public UserGetPostController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/api/user/login")
    public LoginResponse login(@RequestBody UserLoginRequest request) {
        // 전화번호와 비밀번호를 받아 서비스를 통해 인증
        System.out.println(request.getUserPhoneNumber()+request.getUserPassword());
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
