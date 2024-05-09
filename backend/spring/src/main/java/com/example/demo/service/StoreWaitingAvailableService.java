package com.example.demo.service;

import com.example.demo.config.events.StoreInfoUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.ToServer.StoreWaitingAvailableRequest;
import com.example.demo.model.DataBase.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.config.events.EventPublisherService;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.StoreInfoUpdatedEvent;

@Service
public class StoreWaitingAvailableService {

    private final StoreRepository storeRepository;
    @Autowired
    private EventPublisherService eventPublisherService;

    public StoreWaitingAvailableService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public String updateStoreWaitingAvailable(StoreWaitingAvailableRequest request) {
        try {
            Store store = storeRepository.findByStoreCode(request.getStoreCode());

            // 조회된 스토어 정보가 없을 경우
            if (store == null) {
                return "6001";
            }
            store.setStoreWaitingAvailable(request.getStoreWaitingAvailable());
            storeRepository.save(store);
            //이벤트 발생 메소드
            eventPublisherService.publishStoreInfoUpdate(new StoreInfoUpdatedEvent(this, request.getStoreCode()),1000);
            System.out.println(1);
            return "200";
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            return "6002";
        }
    }
}
