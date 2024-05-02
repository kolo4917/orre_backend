package com.example.demo.service;

import com.example.demo.DTO.ToServer.UserSignupRemoveRequest;
import com.example.demo.model.DataBase.User;
import com.example.demo.repository.UserSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SignupRemoveService {

    private final UserSaveRepository userSaveRepository;

    @Autowired
    public SignupRemoveService(UserSaveRepository userSaveRepository) {
        this.userSaveRepository = userSaveRepository;
    }
    @Transactional
    public boolean removeSignup(UserSignupRemoveRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String password = request.getPassword();
        String username = request.getUsername();

        // 사용자 인증
        if (!authenticateUser(phoneNumber, password, username)) {
            System.out.println("사용자 인증에 실패하였습니다.");
            return false;
        }

        // 사용자 삭제 로직 추가
        if (userSaveRepository.existsByPhoneNumber(phoneNumber)) {
            userSaveRepository.deleteByPhoneNumber(phoneNumber);
            System.out.println("사용자 가입 정보를 삭제하였습니다.");
            return true;
        } else {
            System.out.println("가입된 사용자가 없습니다.");
            return false;
        }
    }

    private boolean authenticateUser(String phoneNumber, String password, String username) {
        // 데이터베이스에서 해당 phoneNumber를 가진 사용자를 조회합니다.
        User user = userSaveRepository.findByPhoneNumber(phoneNumber);

        // 조회된 사용자가 없거나 비밀번호와 이름이 일치하지 않으면 인증 실패
        if (user == null || !user.getPassword().equals(password) || !user.getName().equals(username)) {
            return false;
        }

        // 모든 조건이 일치하면 인증 성공
        return true;
    }
}
