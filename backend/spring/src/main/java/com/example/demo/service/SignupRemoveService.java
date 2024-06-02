package com.example.demo.service;

import com.example.demo.DTO.ToServer.UserSignupRemoveRequest;
import com.example.demo.model.DataBase.User;
import com.example.demo.model.DataBase.UserLog;
import com.example.demo.repository.UserSaveRepository;
import com.example.demo.repository.UserLogRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SignupRemoveService {

    private final UserSaveRepository userSaveRepository;
    private final UserLogRepository userLogRepository;
    @Autowired
    public SignupRemoveService(UserSaveRepository userSaveRepository, UserLogRepository userLogRepository) {
        this.userSaveRepository = userSaveRepository;
        this.userLogRepository = userLogRepository;
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
            List<UserLog> userLogs = userLogRepository.findByUserPhoneNumber(phoneNumber);
            if (!userLogs.isEmpty()) {
                userLogRepository.deleteAll(userLogs);
                System.out.println("사용자 로그 정보를 삭제하였습니다.");
            }

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

        if (user == null) {
            System.out.println("사용자를 찾을 수 없습니다.");
            return false;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 조회된 사용자의 이름과 비밀번호가 일치하는지 확인합니다.
        if (!encoder.matches(password, user.getPassword()) || !user.getName().equals(username)) {
            System.out.println("인증 실패: 비밀번호 또는 사용자 이름 불일치");
            return false;
        }

        // 모든 조건이 일치하면 인증 성공
        System.out.println("인증 성공");
        return true;
    }
}
