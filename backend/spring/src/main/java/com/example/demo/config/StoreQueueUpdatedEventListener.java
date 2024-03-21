package com.example.demo.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.service.StoreDynamicQueueService;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;

@Component
public class StoreQueueUpdatedEventListener implements ApplicationListener<StoreQueueUpdatedEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private StoreDynamicQueueService storeDynamicQueueService;

    @Override
    public void onApplicationEvent(StoreQueueUpdatedEvent event) {
        Integer storeCode = event.getStoreCode();
        StoreDynamicQueue dynamicQueue = storeDynamicQueueService.findStoreDynamicQueue(storeCode);
        messagingTemplate.convertAndSend("/topic/user/dynamicStoreWaitingInfo/" + storeCode, dynamicQueue);
    }

}
