package com.example.demo.config.events.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.config.events.StoreInfoUpdatedEvent;
import com.example.demo.service.StoreService;
import com.example.demo.DTO.ToClient.StoreDTO;

@Component
public class StoreInfoUpdateEventListener implements ApplicationListener<StoreInfoUpdatedEvent> {

    @Autowired
    private StoreService storeService; // StoreInfoService에 대한 의존성 주입
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void onApplicationEvent(StoreInfoUpdatedEvent event) {
        // StoreInfoUpdatedEvent 이벤트를 수신하면 처리할 작업을 여기에 구현
        Integer storeCode = event.getStoreCode();
        StoreDTO storeDTO = storeService.getStoreDetailsByStoreCode(storeCode);

        //스토어 정보를 다른 경로로 전송
        messagingTemplate.convertAndSend("/topic/user/storeInfo/"+ storeCode, storeDTO);
        messagingTemplate.convertAndSend("/topic/admin/storeInfo/"+ storeCode, storeDTO);

    }
}
