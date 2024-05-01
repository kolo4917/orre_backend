package com.example.demo.config.events.listener;

import com.example.demo.config.events.UserNoShowEvent;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserNoShowEventListener implements ApplicationListener<UserNoShowEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onApplicationEvent(UserNoShowEvent event) {
        // 이벤트에서 필요한 데이터 추출
        String userPhoneNumber = event.getUserPhoneNumber();
        int storeCode = event.getStoreCode();
        UserStoreWaitResponse userStoreWaitResponse = event.getUserStoreWaitResponse();
        // 메시지 전송
        messagingTemplate.convertAndSend("/topic/user/waiting/cancel/" + storeCode + "/" + userPhoneNumber,
                userStoreWaitResponse);
    }
}
