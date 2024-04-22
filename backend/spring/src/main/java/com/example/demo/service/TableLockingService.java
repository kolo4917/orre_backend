package com.example.demo.service;

import com.example.demo.config.events.StoreQueueUpdatedEvent;
import com.example.demo.model.DataBase.RestaurantTable;
import com.example.demo.repository.TableLockingRepository;
import com.example.demo.config.events.EventPublisherService;
import jakarta.persistence.criteria.CriteriaBuilder;
import com.example.demo.model.DataBase.UserStoreWait;
import com.example.demo.repository.UserStoreWaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableLockingService {
    @Autowired
    private UserStoreWaitRepository userStoreWaitRepository;
    private final TableLockingRepository tableLockingRepository;
    private final EventPublisherService eventPublisherService;

    @Autowired
    public TableLockingService(TableLockingRepository tableLockingRepository, EventPublisherService eventPublisherService) {
        this.tableLockingRepository = tableLockingRepository;
        this.eventPublisherService = eventPublisherService;
    }

    @Transactional // 테이블 이용 상태 변화
    public String unlockTable(Integer storeCode, Integer tableNumber, Integer waitingNumber) {
        // 조건에 맞는 user_store_wait 레코드 찾기
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode, waitingNumber);
        if (userStoreWait.getStatus() == 1) {
            // 해당 가게 코드와 테이블 번호로 restaurant_table 레코드 찾기
            userStoreWait.setStatus(0);
            userStoreWaitRepository.save(userStoreWait);
            eventPublisherService.publishEventAfterDelay(new StoreQueueUpdatedEvent(this, storeCode), 1000); // 1초 딜레이
            RestaurantTable table = tableLockingRepository.findByStoreCodeAndTableNumber(storeCode, tableNumber);
            if ((table != null) && (table.getTableAvailable() != 1)) {
                table.setTableAvailable(1); // 테이블 사용 불가능으로 변경
                table.setTablePhoneNumber(userStoreWait.getPhoneNumber()); // 전화번호 할당
                tableLockingRepository.save(table); // 변경 사항 저장
                return userStoreWait.getPhoneNumber(); // 전화번호 반환
            }
        }
        return null; // 언락 실패 또는 전화번호 없음
    }
    @Transactional // 테이블 이용 상태 변화
    public String lockTable(Integer storeCode, Integer tableNumber) {
        // 조건에 맞는 user_store_wait 레코드 찾기
            // 해당 가게 코드와 테이블 번호로 restaurant_table 레코드 찾기
            String lockedPhoneNumeber;
            RestaurantTable table = tableLockingRepository.findByStoreCodeAndTableNumber(storeCode, tableNumber);
            if ((table != null) && (table.getTableAvailable() != 0)) {
                lockedPhoneNumeber = table.getTablePhoneNumber();
                table.setTableAvailable(0); // 테이블 사용 가능으로 변경
                table.setTablePhoneNumber(null); // 전화번호 할당
                tableLockingRepository.save(table); // 변경 사항 저장
                return lockedPhoneNumeber; // 전화번호 반환
            }

        return null; // 언락 실패 또는 전화번호 없음
    }
}
