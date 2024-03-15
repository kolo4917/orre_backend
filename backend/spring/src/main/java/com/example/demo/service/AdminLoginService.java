package com.example.demo.service;

import com.example.demo.model.DataBase.Admin;
import com.example.demo.repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {

    @Autowired
    private AdminLoginRepository adminRepository;

    public Admin validateAdminCredentials(String phoneNumber, String password) {
        Admin admin = adminRepository.findByAdminPhoneNumberAndAdminPassword(phoneNumber, password);
        return admin;
    }
}
