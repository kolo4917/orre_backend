package com.example.demo.service;

import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserCallService {

    public UserCallResponse callUser(UserCallRequest request) {
        Integer storeCode = request.getStoreCode();
        Integer waitingTeam = request.getWaitingTeam();
        Integer minutesToAdd = request.getMinutesToAdd();

        // 현재 시간
        LocalDateTime currentTime = LocalDateTime.now();

        // 5분 후 입장 예상 시간 계산
        LocalDateTime entryTime = currentTime.plusMinutes(minutesToAdd);

        // 응답 생성
        return new UserCallResponse(storeCode, waitingTeam, entryTime);
    }
}
