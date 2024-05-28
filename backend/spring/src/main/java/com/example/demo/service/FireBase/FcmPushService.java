package com.example.demo.service.FireBase;

import com.example.demo.DTO.ToFireBase.FcmRequest;
import com.example.demo.model.DataBase.User;
import com.example.demo.repository.UserSaveRepository;
import com.example.demo.service.FireBase.FcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FcmPushService {
    @Autowired
    private UserSaveRepository userSaveRepository;
    @Autowired
    private FcmService fcmService;
    public void sendCallNotification(String userPhoneNumber, Integer waitingTeam, String storeName, Integer minutesToAdd) {
        // 사용자 정보 조회
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            // 알림 제목과 내용 설정
            String notificationTitle = "웨이팅 호출 알림";
            String notificationBody = waitingTeam + "번 오리님 " + storeName + " 가게로 " + minutesToAdd + "분 안으로 방문해주세요!";
            FcmRequest.Notification notification = new FcmRequest.Notification(notificationTitle, notificationBody);
            FcmRequest.Message message = new FcmRequest.Message(user.getUserFcmToken(), notification);
            FcmRequest fcmRequest = new FcmRequest(message);

            // FCM 메시지 전송
            try {
                fcmService.sendMessage(fcmRequest);
            } catch (Exception e) {
                // 예외 처리
                e.printStackTrace();
            }
        }
    }

    public void sendNoShowNotification(String userPhoneNumber, String storeName) {
        // 사용자 정보 조회
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            // 알림 제목과 내용 설정
            String notificationTitle = "웨이팅 취소 알림";
            String notificationBody = storeName + " 가게에서 웨이팅을 취소하였습니다.";
            FcmRequest.Notification notification = new FcmRequest.Notification(notificationTitle, notificationBody);
            FcmRequest.Message message = new FcmRequest.Message(user.getUserFcmToken(), notification);
            FcmRequest fcmRequest = new FcmRequest(message);

            // FCM 메시지 전송
            try {
                fcmService.sendMessage(fcmRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendClosingNotification(String userPhoneNumber, String storeName) {
        // 사용자 정보 조회
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            // 알림 제목과 내용 설정
            String notificationTitle = "웨이팅 취소 알림";
            String notificationBody = storeName + " 가게에서 영업을 종료하였습니다.";
            FcmRequest.Notification notification = new FcmRequest.Notification(notificationTitle, notificationBody);
            FcmRequest.Message message = new FcmRequest.Message(user.getUserFcmToken(), notification);
            FcmRequest fcmRequest = new FcmRequest(message);

            // FCM 메시지 전송
            try {
                fcmService.sendMessage(fcmRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
