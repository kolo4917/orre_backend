package com.example.demo.service;

import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.config.events.UserCallEvent;
import com.example.demo.config.events.EventPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserCallService {

    @Autowired
    private EventPublisherService eventPublisherService;

    public UserCallResponse callUser(UserCallRequest request) {
        Integer storeCode = request.getStoreCode();
        Integer waitingTeam = request.getWaitingTeam();
        Integer minutesToAdd = request.getMinutesToAdd();

        // 현재 시간
        LocalDateTime currentTime = LocalDateTime.now();

        // 5분 후 입장 예상 시간 계산
        LocalDateTime entryTime = currentTime.plusMinutes(minutesToAdd);

        // UserCallResponse 생성
        UserCallResponse userCallResponse = new UserCallResponse(storeCode, waitingTeam, entryTime);

        // 이벤트 발행
        eventPublisherService.publishUserCallEventAfterDelay(new UserCallEvent(this, userCallResponse), 1000); // 1초 딜레이

        // 응답 생성
        return userCallResponse;
    }
}
