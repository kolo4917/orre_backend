package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.DTO.ToServer.UserStoreWaitRequest;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.EventPublisherService;

@Service
public class UserStoreMakeWaitingService {

    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private EventPublisherService eventPublisherService;

    @Transactional
    public UserStoreWait createUserStoreWait(UserStoreWaitRequest request) {
        Integer maxWaiting = userStoreWaitRepository.findMaxWaitingByStoreCode(request.getStoreCode());
        Integer nextWaiting = (maxWaiting == null) ? 1 : maxWaiting + 1;

        UserStoreWait userStoreWait = userStoreWaitRepository.findByPhoneNumberAndStoreCode(request.getPhoneNumber(), request.getStoreCode());

        if (userStoreWait != null) {
            // 이미 존재하는 경우, 상태와 인원 수 업데이트
            userStoreWait.setStatus(1); // 1 - 대기중
            userStoreWait.setPersonNumber(request.getPersonNumber());
            userStoreWait.setWaiting(nextWaiting); // 최대 대기 번호로 대기 번호 갱신
        } else {
            // 새로운 대기열 추가
            userStoreWait = new UserStoreWait(
                    request.getPhoneNumber(),
                    request.getStoreCode(),
                    nextWaiting,
                    1, // 1 - 대기중
                    request.getPersonNumber()
            );
        }

        UserStoreWait savedUserStoreWait = userStoreWaitRepository.save(userStoreWait);

        // 이벤트 발행
        eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, request.getStoreCode()), 1000); // 1초 딜레이

        return savedUserStoreWait;
    }

    public boolean deactivateUserStoreWaitByPhoneNumber(UserStoreWaitRequest request) {
        UserStoreWait userStoreWait = userStoreWaitRepository.findByPhoneNumberAndStoreCode(request.getPhoneNumber(), request.getStoreCode());
        if (userStoreWait != null) {
            userStoreWait.setStatus(0); // 상태를 비활성화로 변경
            userStoreWaitRepository.save(userStoreWait);
            // 이벤트 발행
            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, request.getStoreCode()), 1000); // 1초 딜레이
            return true;
        }
        return false;
    }
}
