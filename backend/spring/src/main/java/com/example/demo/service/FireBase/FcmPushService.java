package com.example.demo.service.FireBase;

import com.example.demo.DTO.ToFireBase.FcmRequest;
import com.example.demo.model.DataBase.Admin;
import com.example.demo.model.DataBase.User;
import com.example.demo.repository.UserSaveRepository;
import com.example.demo.repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FcmPushService {

    @Autowired
    private UserSaveRepository userSaveRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Autowired
    private FcmService fcmService;

    @Autowired
    private FcmServiceForGaorre fcmServiceForGaorre;


    private FcmRequest createFcmRequest(String token, String title, String body) {
        // Create the notification
        FcmRequest.Notification notification = new FcmRequest.Notification(title, body);

        // Create Android notification settings
        FcmRequest.Android.AndroidNotification androidNotification = new FcmRequest.Android.AndroidNotification("default");
        FcmRequest.Android android = new FcmRequest.Android(androidNotification);

        // Create APNs notification settings
        FcmRequest.Apns.ApnsPayload.Aps aps = new FcmRequest.Apns.ApnsPayload.Aps("default");
        FcmRequest.Apns.ApnsPayload apnsPayload = new FcmRequest.Apns.ApnsPayload(aps);
        FcmRequest.Apns apns = new FcmRequest.Apns(apnsPayload);

        // Create the message
        FcmRequest.Message message = new FcmRequest.Message(token, notification, android, apns);
        return new FcmRequest(message);
    }

    public Integer sendCallNotification(String userPhoneNumber, Integer waitingTeam, String storeName, Integer minutesToAdd) {
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            String notificationTitle = "웨이팅 호출 알림";
            String notificationBody = waitingTeam + "번 오리님 " + storeName + " 가게로 " + minutesToAdd + "분 안으로 방문해주세요!";
            FcmRequest fcmRequest = createFcmRequest(user.getUserFcmToken(), notificationTitle, notificationBody);

            try {
                fcmService.sendMessage(fcmRequest);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            return -1;
        }
    }

    public void sendNoShowNotification(String userPhoneNumber, String storeName) {
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            String notificationTitle = "웨이팅 취소 알림";
            String notificationBody = storeName + " 가게에서 웨이팅을 취소하였습니다.";
            FcmRequest fcmRequest = createFcmRequest(user.getUserFcmToken(), notificationTitle, notificationBody);

            try {
                fcmService.sendMessage(fcmRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendClosingNotification(String userPhoneNumber, String storeName) {
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);
        if (user != null && user.getUserFcmToken() != null) {
            String notificationTitle = "웨이팅 취소 알림";
            String notificationBody = storeName + " 가게에서 영업을 종료하였습니다.";
            FcmRequest fcmRequest = createFcmRequest(user.getUserFcmToken(), notificationTitle, notificationBody);

            try {
                fcmService.sendMessage(fcmRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendUserWaitingMakeNotification(Integer storeCode, Integer personNumber) {
        Admin admin = adminLoginRepository.findByAdminStoreCode(storeCode);
        if (admin != null && admin.getAdminFcmToken() != null) {
            String notificationTitle = "신규 웨이팅 접수 알림";
            String notificationBody = personNumber + "명 팀의 신규 웨이팅이 접수되었습니다.";
            FcmRequest fcmRequest = createFcmRequest(admin.getAdminFcmToken(), notificationTitle, notificationBody);

            try {
                fcmServiceForGaorre.sendMessage(fcmRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
