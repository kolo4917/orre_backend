package com.example.demo.service;
import com.example.demo.DTO.ToClient.UserStoreWaitResponse;
import com.example.demo.config.events.UserNoShowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.EventPublisherService;

import java.util.List;
@Service
public class StoreClosingService {

    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private EventPublisherService eventPublisherService;
    @Autowired
    private UserLogService userLogService;

    @Transactional
    public String closeStore(Integer storeCode) {
        try {
            List<UserStoreWait> usersInQueue = userStoreWaitRepository.findByStoreCodeAndStatus(storeCode, 1);
            if (usersInQueue.isEmpty()) {
                // 대기열이 비어있는 경우
                return "6402";
            }
            for (UserStoreWait user : usersInQueue) {
                userLogService.modifyWaitingByClosing(user.getUserPhoneNumber(), storeCode, "store canceled");
                userStoreWaitRepository.delete(user); // 특정 행을 삭제합니다.

                UserStoreWaitResponse userStoreWaitResponse = new UserStoreWaitResponse();
                userStoreWaitResponse.setStatus("1103");
                userStoreWaitResponse.setToken(null); // 필요하다면 여기에 상세 정보를 설정할 수 있습니다.
                eventPublisherService.publishNoShowUserEventAfterDelay(new UserNoShowEvent(this, user.getUserPhoneNumber(), storeCode, userStoreWaitResponse), 1000);

            }
            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이
            return "200";
        } catch (EmptyResultDataAccessException e) {
            // storeCode에 해당하는 가게가 없는 경우
            return "6401";
        }
    }
}
