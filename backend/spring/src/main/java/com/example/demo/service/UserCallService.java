package com.example.demo.service;

import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.config.events.UserCallEvent;
import com.example.demo.config.events.EventPublisherService;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.service.UserLogService;
import com.example.demo.repository.UserStoreWaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserCallService {
    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;

    public UserCallResponse callUser(UserCallRequest request) {
        Integer storeCode = request.getStoreCode();
        Integer waitingTeam = request.getWaitingTeam();
        Integer minutesToAdd = request.getMinutesToAdd();

        // 현재 시간
        LocalDateTime currentTime = LocalDateTime.now();

        // n분 후 입장 예상 시간 계산
        LocalDateTime entryTime = currentTime.plusMinutes(minutesToAdd);

        // UserCallResponse 생성
        UserCallResponse userCallResponse = new UserCallResponse(storeCode, waitingTeam, entryTime);
        // 번호 조회
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode,waitingTeam);
        String userPhoneNumber = userStoreWait.getUserPhoneNumber();

        // 로그 기록
        userLogService.modifyWaiting(userPhoneNumber, request.getStoreCode(), "called : "+minutesToAdd);

        // 이벤트 발행
        eventPublisherService.publishUserCallEventAfterDelay(new UserCallEvent(this, userCallResponse), 1000); // 1초 딜레이

        // 응답 생성
        return userCallResponse;
    }
}
