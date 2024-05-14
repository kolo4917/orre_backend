package com.example.demo.service;

import com.example.demo.config.events.EventPublisherService;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import com.example.demo.config.events.UserNoShowEvent;
import com.example.demo.DTO.ToServer.StoreEnterRequest;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.DTO.ToClient.StatusResponse;

import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.DTO.ToClient.BooleanResponse;
@Service
public class StoreEnteringService {

    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired UserLogService userLogService;
    private final UserStoreWaitRepository userStoreWaitRepository;

    @Autowired
    public StoreEnteringService(UserStoreWaitRepository userStoreWaitRepository) {
        this.userStoreWaitRepository = userStoreWaitRepository;
    }

    @Transactional
    public String handleEnteringService(StoreEnterRequest request) {
        // 특정 유저의 웨이팅 삭제
        int EnteringUserCode = request.getWaitingNumber();
        int storeCode = request.getStoreCode();
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode, EnteringUserCode);
        if (userStoreWait != null && userStoreWait.getStoreCode().equals(storeCode)) {
            String enteringUserPhoneNumber = userStoreWait.getUserPhoneNumber();
            userStoreWaitRepository.delete(userStoreWait); // 특정 행을 삭제합니다.
            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이

            UserStoreWaitResponse userStoreWaitResponse = new UserStoreWaitResponse();
            userStoreWaitResponse.setStatus("1104");
            userStoreWaitResponse.setToken(null); // 필요하다면 여기에 상세 정보를 설정할 수 있습니다.
            //로그 기록
            userLogService.modifyWaiting(userStoreWait.getUserPhoneNumber(), userStoreWait.getStoreCode(), "entered");
            eventPublisherService.publishNoShowUserEventAfterDelay(new UserNoShowEvent(this, enteringUserPhoneNumber, storeCode, userStoreWaitResponse), 1000);
            return "200";
        } else {
            return "6201";
        }
    }
}
