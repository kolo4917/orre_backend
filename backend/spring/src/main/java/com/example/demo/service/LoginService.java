package com.example.demo.service;

import com.example.demo.model.DataBase.Admin;
import com.example.demo.model.DataBase.User;
import com.example.demo.repository.AdminLoginRepository;
import com.example.demo.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminLoginRepository adminRepository;

    @Autowired
    private UserLoginRepository userRepository;

    public Admin validateAdminCredentials(String phoneNumber, String password) {
        Admin admin = adminRepository.findByAdminPhoneNumberAndAdminPassword(phoneNumber, password);
        return admin;
    }

    public User validateUserCredentials(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumberAndPassword(phoneNumber, password);
        return user;
    }
}
