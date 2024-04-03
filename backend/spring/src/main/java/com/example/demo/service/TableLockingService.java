package com.example.demo.service;

import com.example.demo.model.DataBase.RestaurantTable;
import com.example.demo.repository.TableLockingRepository;
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

    @Autowired
    public TableLockingService(TableLockingRepository tableLockingRepository) {
        this.tableLockingRepository = tableLockingRepository;
    }

    @Transactional // 테이블 이용 상태 변화
    public boolean unlockTable(Integer storeCode, Integer tableNumber, Integer waitingNumber) {
        // 조건에 맞는 user_store_wait 레코드 찾기
        UserStoreWait userStoreWait = userStoreWaitRepository.findByStoreCodeAndWaitingNumber(storeCode, waitingNumber);
        if (userStoreWait != null) {
            // 해당 가게 코드와 테이블 번호로 restaurant_table 레코드 찾기
            RestaurantTable table = tableLockingRepository.findByStoreCodeAndTableNumber(storeCode, tableNumber);
            if (table != null && table.getTableAvailable() != 1) {
                table.setTableAvailable(1); // 테이블 사용 가능으로 변경
                table.setTablePhoneNumber(userStoreWait.getPhoneNumber()); // 전화번호로 인원 수 설정(가정)
                tableLockingRepository.save(table); // 변경 사항 저장
                return true; // 테이블 언락 성공
            }
        }
        return false; // 해당하는 테이블 또는 대기 번호를 찾지 못한 경우
    }
}
