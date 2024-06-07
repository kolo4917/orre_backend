package com.example.demo.service;

import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.config.events.UserCallEvent;
import com.example.demo.config.events.EventPublisherService;
import com.example.demo.service.FireBase.FcmPushService;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.model.DataBase.Store;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.repository.StoreRepository;
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
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private FcmPushService fcmPushService;
    @Autowired
    private KakaoCallService kakaoPushService;

    public UserCallResponse callUser(UserCallRequest request) {
        Integer storeCode = request.getStoreCode();
        Integer waitingTeam = request.getWaitingTeam();
        Integer minutesToAdd = request.getMinutesToAdd();

        // 현재 시간 가져오기
        LocalDateTime currentTime = LocalDateTime.now();

        // n분 후 입장 예상 시간 계산
        LocalDateTime entryTime = currentTime.plusMinutes(minutesToAdd);

        // UserCallResponse 객체 생성
        UserCallResponse userCallResponse = new UserCallResponse(storeCode, waitingTeam, entryTime);

        // 대기 번호 조회
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode, waitingTeam);
        String userPhoneNumber = userStoreWait.getUserPhoneNumber();

        // 가게 이름 조회
        Store store = storeRepository.findByStoreCode(storeCode);
        String storeName = store.getStoreName();

        // 로그 기록
        userLogService.modifyWaiting(userPhoneNumber, storeCode, "called : " + minutesToAdd);

        // 이벤트 발행 (1초 딜레이 후)
        eventPublisherService.publishUserCallEventAfterDelay(new UserCallEvent(this, userCallResponse), 1000); // 1초 딜레이

        // FCM 알림 전송
        fcmPushService.sendCallNotification(userPhoneNumber, waitingTeam, storeName, minutesToAdd);
        //FCM 알림 전송이 fail 일 경우 카카오톡 알림 전송
        kakaoPushService.sendOneAta(userPhoneNumber,waitingTeam,storeName,minutesToAdd);
        // 응답 반환
        return userCallResponse;
    }
}