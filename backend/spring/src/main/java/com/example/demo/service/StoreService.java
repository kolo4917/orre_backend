package com.example.demo.service;

import com.example.demo.DTO.StoreDTO;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDTO getStoreDetailsByStoreCode(Integer storeCode) {
        Object[] storeDetails = storeRepository.findStoreDetailsByStoreCode(storeCode);
        System.out.println("storeDetails 배열의 길이: " + storeDetails.length);
        if (storeDetails != null && storeDetails.length > 0) {
            Object firstElement = storeDetails[0];
            if (firstElement instanceof Object[]) {
                // 이중 배열인 경우
                Object[] innerArray = (Object[]) firstElement;
                StoreDTO storeDTO = new StoreDTO();
                // 쿼리 결과를 StoreDTO 필드에 매핑
                storeDTO.setStoreCode(String.valueOf(innerArray[0])); // storeCode를 String으로 변환
                storeDTO.setStoreName((String) innerArray[1]);
                storeDTO.setNumberOfTeamsWaiting((Integer) innerArray[2]);
                storeDTO.setStoreInfoVersion((Integer) innerArray[3]);
                return storeDTO;
            }
        }
        return null;
    }
}
