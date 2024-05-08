package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DTO.ToServer.StoreWaitingAvailableRequest;
import com.example.demo.model.DataBase.Store;
import com.example.demo.repository.StoreRepository;

@Service
public class StoreWaitingAvailableService {

    private final StoreRepository storeRepository;

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

            return "200";
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            return "6002";
        }
    }
}
