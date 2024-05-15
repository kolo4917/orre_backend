package com.example.demo.service;

import com.example.demo.DTO.ToClient.UserCallResponse;
import com.example.demo.DTO.ToServer.UserCallRequest;
import com.example.demo.config.events.UserCallEvent;
import com.example.demo.config.events.EventPublisherService;
import com.example.demo.service.FireBase.FcmService;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.model.DataBase.User;
import com.example.demo.model.DataBase.Store;
import com.example.demo.DTO.ToFireBase.FcmRequest;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.repository.UserSaveRepository;
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
    private UserSaveRepository userSaveRepository;
    @Autowired
    private FcmService fcmService;
    @Autowired
    private StoreRepository storeRepository;

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

        //FCM 가져오는 로직
        String userPhoneNumber = userStoreWait.getUserPhoneNumber();
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        String userFcmToken = user.getUserFcmToken();

        //스토어 코드로 가게 이름 조회
        Store store= storeRepository.findByStoreCode(storeCode);
        String storeName = store.getStoreName();

        // 로그 기록
        userLogService.modifyWaiting(userPhoneNumber, request.getStoreCode(), "called : "+minutesToAdd);

        // 이벤트 발행
        eventPublisherService.publishUserCallEventAfterDelay(new UserCallEvent(this, userCallResponse), 1000); // 1초 딜레이

        // FcmRequest 객체 생성
        FcmRequest.Notification notification = new FcmRequest.Notification("웨이팅 호출 알림", waitingTeam + "번 " + "오리님 " + storeName + " 가게로 " + minutesToAdd + "분 " + "안으로 방문해주세요!");
        FcmRequest.Message message = new FcmRequest.Message(userFcmToken, notification);
        FcmRequest fcmRequest = new FcmRequest(message);

        // FCM 메시지 전송
        try {
            fcmService.sendMessage(fcmRequest);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
        System.out.println(7777);
        // 응답 생성
        return userCallResponse;
    }
}
