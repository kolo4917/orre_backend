package com.example.demo.service;

import com.example.demo.DTO.ToClient.LogResponse;
import com.example.demo.model.DataBase.UserLog;
import com.example.demo.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogQueryService {

    @Autowired
    private UserLogRepository userLogRepository;

    public LogResponse getUserLogs(String phoneNumber) {
        // 사용자의 로그 조회
        List<UserLog> userLogs = userLogRepository.findByUserPhoneNumber(phoneNumber);

        // 응답 생성
        if (userLogs != null && !userLogs.isEmpty()) {
            return new LogResponse(userLogs, "200");
        } else {
            return new LogResponse(null, "1201");
        }
    }

    public LogResponse getStoreLogs(int storeCode) {
        // 매장의 로그 조회
        List<UserLog> storeLogs = userLogRepository.findByStoreCode(storeCode);

        // 응답 생성
        if (storeLogs != null && !storeLogs.isEmpty()) {
            return new LogResponse(storeLogs, "200");
        } else {
            return new LogResponse(null, "6301");
        }
    }
}
