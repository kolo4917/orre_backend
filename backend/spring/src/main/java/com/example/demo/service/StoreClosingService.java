package com.example.demo.service;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import com.example.demo.config.events.UserNoShowEvent;
import com.example.demo.model.DataBase.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.EventPublisherService;

import com.example.demo.service.FireBase.FcmPushService;

import java.util.List;
@Service
public class StoreClosingService {

    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private FcmPushService fcmPushService;
    @Transactional
    public String closeStore(Integer storeCode) {
        try {
            List<UserStoreWait> usersInQueue = userStoreWaitRepository.findByStoreCodeAndStatus(storeCode, 1);
            if (usersInQueue.isEmpty()) {
                // 대기열이 비어있는 경우
                return "6402";
            }
            Store store= storeRepository.findByStoreCode(storeCode);
            String storeName = store.getStoreName();

            for (UserStoreWait user : usersInQueue) {
                userLogService.modifyWaitingByClosing(user.getUserPhoneNumber(), storeCode, "store canceled");
                user.setStatus(0); // 상태를 비활성화로 변경
                userStoreWaitRepository.save(user);
                UserStoreWaitResponse userStoreWaitResponse = new UserStoreWaitResponse();
                userStoreWaitResponse.setStatus("1106");
                userStoreWaitResponse.setToken(null);
                String userPhoneNumber = user.getUserPhoneNumber();
                //fcm push service
                fcmPushService.sendClosingNotification(userPhoneNumber, storeName);
                eventPublisherService.publishNoShowUserEventAfterDelay(new UserNoShowEvent(this, user.getUserPhoneNumber(), storeCode, userStoreWaitResponse), 1000);

            }
            List<UserStoreWait> usersInQueueWith0 = userStoreWaitRepository.findByStoreCodeAndStatus(storeCode, 0);

            for (UserStoreWait user: usersInQueueWith0){
                userStoreWaitRepository.delete(user);
            }

            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이
            return "200";
        } catch (EmptyResultDataAccessException e) {
            // storeCode에 해당하는 가게가 없는 경우
            return "6401";
        }
    }
}
