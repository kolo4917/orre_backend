package com.example.demo.service.FireBase;

import com.example.demo.DTO.ToFireBase.FcmMessage;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    public void sendMessage(FcmMessage fcmMessage) {
        // FCM 메시지 빌더를 사용하여 메시지 객체를 생성합니다.
        Notification notification = Notification.builder()
                .setTitle(fcmMessage.getNotification().getTitle())
                .setBody(fcmMessage.getNotification().getBody())
                .build();

        // FCM 토큰이 null이 아닌지 확인
        if (fcmMessage.getToken() != null) {
            Message message = Message.builder()
                    .setToken(fcmMessage.getToken())
                    .setNotification(notification)
                    .build();

            try {
                // FCM 메시지를 전송하고 응답을 출력합니다.
                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message: " + response);
            } catch (Exception e) {
                // 메시지 전송 중 예외가 발생하면 스택 트레이스를 출력합니다.
                e.printStackTrace();
            }
        } else {
            System.out.println("FCM 토큰이 null입니다. 메시지를 전송하지 않습니다.");
        }
    }
}
