package com.example.demo.config.events.listener;

import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.service.StoreDynamicQueueService;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueue;

@Component
public class StoreQueueUpdatedEventListener implements ApplicationListener<StoreQueueUpdatedEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private StoreDynamicQueueService storeDynamicQueueService;

    @Override
    public void onApplicationEvent(StoreQueueUpdatedEvent event) {
        Integer storeCode = event.getStoreCode();

        // 기본 대기열 정보 가져오기
        StoreDynamicQueue dynamicQueue = storeDynamicQueueService.findStoreDynamicQueue(storeCode);
        // 확장된 대기열 정보 가져오기
        ExtendedStoreDynamicQueue extendedDynamicQueue = storeDynamicQueueService.findStoreByExtendedQueue(storeCode);

        // 각각의 대기열 정보를 다른 경로로 전송
        messagingTemplate.convertAndSend("/topic/user/dynamicStoreWaitingInfo/" + storeCode, dynamicQueue);
        messagingTemplate.convertAndSend("/topic/admin/dynamicStoreWaitingInfo/" + storeCode, extendedDynamicQueue);
    }

}
