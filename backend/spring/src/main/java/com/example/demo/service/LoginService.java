package com.example.demo.service;

import com.example.demo.model.DataBase.Admin;
import com.example.demo.model.DataBase.User;
import com.example.demo.repository.AdminLoginRepository;
import com.example.demo.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminLoginRepository adminRepository;

    @Autowired
    private UserLoginRepository userRepository;
    public String saveFcmToken(String phoneNumber, String userFcmToken){
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            user.setUserFcmToken(userFcmToken);
            userRepository.save(user);
            return "200";
        } else {
            return "602";
        }
    }
    public String saveAdminFcmToken(String phoneNumber, String adminFcmToken ){
        Admin admin = adminRepository.findByAdminPhoneNumber(phoneNumber);
        if (admin != null) {
            admin.setAdminFcmToken(adminFcmToken);
            adminRepository.save(admin);
            return "200";
        } else {
            return "602";
        }
    }


    public Admin validateAdminCredentials(String phoneNumber, String password) {
        Admin admin = adminRepository.findByAdminPhoneNumber(phoneNumber);
        if (admin != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 저장된 해시된 비밀번호와 입력된 비밀번호를 비교
            if (passwordEncoder.matches(password, admin.getAdminPassword())) {
                return admin;  // 비밀번호가 일치하면 사용자 객체 반환
            }
        }
        else {
            System.out.println("해당 번호의 사용자를 찾을 수 없습니다.");
        }
        return null;  // 사용자가 없으면 null 반환
    }

    public User validateUserCredentials(String phoneNumber, String password) {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // 저장된 해시된 비밀번호와 입력된 비밀번호를 비교
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;  // 비밀번호가 일치하면 사용자 객체 반환
            }
        }
        else {
            System.out.println("해당 번호의 사용자를 찾을 수 없습니다.");
        }
        return null;  // 사용자가 없으면 null 반환
    }
}
