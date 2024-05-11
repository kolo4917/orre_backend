package com.example.demo.config.events.listener;
import com.example.demo.config.events.StoreMenuOrderEvent;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import com.example.demo.service.StoreMenuOrderService;
import com.example.demo.DTO.ToServer.StoreMenuOrderRequest;

@Component
public class StoreMenuOrderEventListener implements ApplicationListener<StoreMenuOrderEvent> {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private StoreMenuOrderService storeMenuOrderService;
    @Override
    public void onApplicationEvent(StoreMenuOrderEvent event) {
        StoreMenuOrderRequest storeMenuOrderRequest = event.getRequest();
        int storeCode = storeMenuOrderRequest.getStoreCode();

        // 주문 정보를 다른 경로로 전송
        messagingTemplate.convertAndSend("/topic/admin/StoreAdmin/order/" + storeCode, storeMenuOrderRequest);
    }
}
