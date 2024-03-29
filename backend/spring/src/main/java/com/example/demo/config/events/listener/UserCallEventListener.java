package com.example.demo.config.events.listener;

import com.example.demo.config.events.UserCallEvent;
import com.example.demo.service.StoreDynamicQueueService;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserCallEventListener implements ApplicationListener<UserCallEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private StoreDynamicQueueService storeDynamicQueueService;

    @Override
    public void onApplicationEvent(UserCallEvent event) {
        Integer storeCode = event.getUserCallResponse().getStoreCode();
        Integer waitingNumber = event.getUserCallResponse().getWaitingTeam();

        // 대기열 정보를 메시지로 변환하여 해당 경로로 전송
        messagingTemplate.convertAndSend("/topic/user/userCall/" + storeCode+ "/" + waitingNumber, event.getUserCallResponse());
    }
}
