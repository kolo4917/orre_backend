package com.example.demo.config.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Async
    public void publishEventAfterDelay(StoreQueueUpdatedEvent event, long delayMillis) {
        try {
            Thread.sleep(delayMillis); // 딜레이 시간 만큼 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventPublisher.publishEvent(event); // 딜레이 후 이벤트 발행
    }

    @Async
    public void publishUserCallEventAfterDelay(UserCallEvent event, long delayMillis) {
        try {
            Thread.sleep(delayMillis); // 딜레이 시간 만큼 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventPublisher.publishEvent(event); // 딜레이 후 이벤트 발행 //UserCallEvent가 발행되고 listener에서 실행됨
    }
    @Async
    public void publishNoShowUserEventAfterDelay(UserNoShowEvent event, long delayMillis) {
        try {
            Thread.sleep(delayMillis); // 딜레이 시간 만큼 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventPublisher.publishEvent(event); // 딜레이 후 이벤트 발행
    }
    @Async
    public void publishStoreInfoUpdate(StoreInfoUpdatedEvent event, long delayMillis) {
        try {
            Thread.sleep(delayMillis); // 딜레이 시간 만큼 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventPublisher.publishEvent(event); // 딜레이 후 이벤트 발행
    }
    @Async
    public void publishStoreOrder(StoreMenuOrderEvent event, long delayMillis){
        try {
            Thread.sleep(delayMillis); // 딜레이 시간 만큼 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventPublisher.publishEvent(event); // 딜레이 후 이벤트 발행
    }
}
