package com.example.demo.service;
import com.example.demo.config.events.EventPublisherService;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.DTO.ToClient.BooleanResponse;

@Service
public class NoShowService {

    @Autowired
    private EventPublisherService eventPublisherService;
    private final UserStoreWaitRepository userStoreWaitRepository;

    @Autowired
    public NoShowService(UserStoreWaitRepository userStoreWaitRepository) {
        this.userStoreWaitRepository = userStoreWaitRepository;
    }

    @Transactional
    public BooleanResponse handleNoShowCustomers(Integer noShowUserCode, Integer storeCode) {
        // 특정 유저의 status를 0으로 업데이트
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode,noShowUserCode);
        if (userStoreWait != null && userStoreWait.getStoreCode().equals(storeCode)) {
            userStoreWait.setStatus(0);
            userStoreWaitRepository.save(userStoreWait);
            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이
            return new BooleanResponse(true); // 성공 시 true 반환
        } else {
            return new BooleanResponse(false); // 실패 시 false 반환
        }
    }
}

