package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.DTO.ToServer.UserStoreWaitRequest;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.model.DataBase.Store;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.EventPublisherService;

import com.example.demo.service.FireBase.FcmPushService;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
public class UserStoreMakeWaitingService {

    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private FcmPushService fcmPushService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private KakaoWaitingMakeService kakaoWaitingMakeService;

    @Transactional
    public UserStoreWait createUserStoreWait(UserStoreWaitRequest request) {

        Integer storeCode = request.getStoreCode();
        Store store = storeRepository.findByStoreCode(storeCode);
        Integer available = store.getStoreWaitingAvailable();
        List<UserStoreWait> userStoreWaitList = userStoreWaitRepository.findByStoreCodeAndStatus(storeCode,1);
        int listSize = userStoreWaitList.size();
        if(available == 1){
            return new UserStoreWait(request.getUserPhoneNumber(), request.getStoreCode(),-1,1101,-1);
        }
        Integer maxWaiting = userStoreWaitRepository.findMaxWaitingByStoreCode(request.getStoreCode());
        Integer nextWaiting = (maxWaiting == null) ? 1 : maxWaiting + 1;

        UserStoreWait userStoreWait = userStoreWaitRepository.findByPhoneNumberAndStoreCode(request.getUserPhoneNumber(), request.getStoreCode());
        if(userStoreWait != null && userStoreWait.getStatus() == 1){
            return new UserStoreWait(request.getUserPhoneNumber(), request.getStoreCode(),-1,1105,-1);
        }
        if (userStoreWait != null) {
            // 이미 존재하는 경우, 상태와 인원 수 업데이트
            userStoreWait.setStatus(1); // 1 - 대기중
            userStoreWait.setPersonNumber(request.getPersonNumber());
            userStoreWait.setWaiting(nextWaiting); // 최대 대기 번호로 대기 번호 갱신
        } else {
            // 새로운 대기열 추가
            userStoreWait = new UserStoreWait(
                    request.getUserPhoneNumber(),
                    request.getStoreCode(),
                    nextWaiting,
                    1, // 1 - 대기중
                    request.getPersonNumber()
            );
        }

        UserStoreWait savedUserStoreWait = userStoreWaitRepository.save(userStoreWait);
        // 로그 기록
        userLogService.makeWaiting(request.getUserPhoneNumber(), request.getStoreCode(),nextWaiting,request.getPersonNumber());
        // 이벤트 발행
        // 관리자에게 fcm 알림 전송
        fcmPushService.sendUserWaitingMakeNotification(storeCode, userStoreWait.getPersonNumber());
        kakaoWaitingMakeService.sendOneAta(request.getUserPhoneNumber(),nextWaiting, store.getStoreName(),listSize*5);

        // 트랜잭션이 완료된 후에 이벤트 발행
        // 트랜잭션이 완료된 후에 이벤트 발행
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, request.getStoreCode()), 1000); // 1초 딜레이
            }
        });


        return savedUserStoreWait;
    }

    @Transactional
    public boolean deactivateUserStoreWaitByPhoneNumber(UserStoreWaitRequest request) {
        UserStoreWait userStoreWait = userStoreWaitRepository.findByPhoneNumberAndStoreCode(request.getUserPhoneNumber(), request.getStoreCode());
        if (userStoreWait != null) {
            userStoreWait.setStatus(0); // 상태를 비활성화로 변경
            userStoreWaitRepository.save(userStoreWait);
            // 로그 기록
            userLogService.modifyWaiting(request.getUserPhoneNumber(), request.getStoreCode(), "user canceled");
            // 이벤트 발행
            // 트랜잭션이 완료된 후에 이벤트 발행
            // 트랜잭션이 완료된 후에 이벤트 발행
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, request.getStoreCode()), 1000); // 1초 딜레이
                }
            });

            return true;
        }
        return false;
    }
}
