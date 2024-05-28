package com.example.demo.service;
import com.example.demo.DTO.ToFireBase.FcmRequest;
import com.example.demo.config.events.EventPublisherService;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import com.example.demo.config.events.UserCallEvent;
import com.example.demo.config.events.UserNoShowEvent;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import com.example.demo.model.DataBase.Store;
import com.example.demo.model.DataBase.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.service.FireBase.FcmPushService;
import com.example.demo.service.FireBase.FcmService;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.repository.UserSaveRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.DTO.ToClient.BooleanResponse;

@Service
public class NoShowService {

    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private FcmService fcmService;
    @Autowired
    private FcmPushService fcmPushService;
    private final UserStoreWaitRepository userStoreWaitRepository;
    private final UserSaveRepository userSaveRepository;
    private final StoreRepository storeRepository;
    @Autowired
    public NoShowService(UserStoreWaitRepository userStoreWaitRepository, UserSaveRepository userSaveRepository, StoreRepository storeRepository) {
        this.userStoreWaitRepository = userStoreWaitRepository;
        this.userSaveRepository = userSaveRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public BooleanResponse handleNoShowCustomers(Integer noShowUserCode, Integer storeCode) {
        // 특정 유저의 웨이팅 삭제
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode,noShowUserCode);
        String userPhoneNumber = userStoreWait.getUserPhoneNumber();
        User user = userSaveRepository.findByPhoneNumber(userPhoneNumber);

        Store store= storeRepository.findByStoreCode(storeCode);
        String storeName = store.getStoreName();

        String userFcmToken = null;
        if(user != null){
            userFcmToken = user.getUserFcmToken();
        }
        if (userStoreWait != null && userStoreWait.getStoreCode().equals(storeCode)) {
            String noShowUserPhoneNumber = userStoreWait.getUserPhoneNumber();
            userStoreWait.setStatus(0); // 상태를 비활성화로 변경
            userStoreWaitRepository.save(userStoreWait);

            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이

            UserStoreWaitResponse userStoreWaitResponse = new UserStoreWaitResponse();
            userStoreWaitResponse.setStatus("1103");
            userStoreWaitResponse.setToken(null); // 필요하다면 여기에 상세 정보를 설정할 수 있습니다.
            //로그 기록
            userLogService.modifyWaiting(userStoreWait.getUserPhoneNumber(),userStoreWait.getStoreCode(),"store canceled");

            eventPublisherService.publishNoShowUserEventAfterDelay(new UserNoShowEvent(this, noShowUserPhoneNumber, storeCode, userStoreWaitResponse), 1000);

            fcmPushService.sendNoShowNotification(userPhoneNumber, storeName);


            return new BooleanResponse(true); // 성공 시 true 반환
        } else {
            return new BooleanResponse(false); // 실패 시 false 반환
        }
    }
}

