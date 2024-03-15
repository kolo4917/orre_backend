package com.example.demo.service;

import com.example.demo.repository.StoreDynamicQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;

@Service
public class StoreDynamicQueueService {

    private final StoreDynamicQueueRepository storeDynamicQueueRepository;

    @Autowired
    public StoreDynamicQueueService(StoreDynamicQueueRepository storeDynamicQueueRepository) {
        this.storeDynamicQueueRepository = storeDynamicQueueRepository;
    }
    public List<StoreDynamicQueue> findStoreDynamicQueue(Integer storeCode) {
        List<StoreDynamicQueue> dynamicQueues = new ArrayList<>();
        // 레포지토리를 통해 가게의 동적 대기열 정보를 조회
        List<Object[]> dynamicQueueData = storeDynamicQueueRepository.findStoreDynamicQueue(storeCode);

        // 조회된 데이터를 StoreDynamicQueue 객체로 변환하여 리스트에 추가
        for (Object[] data : dynamicQueueData) {
            int storeCode2 = (int) data[0];
            int numberOfTeamsWaiting = (int) data[1];
            int estimatedWaitingTime = 5 * numberOfTeamsWaiting; // 시간 계산 알고리즘 추가 필요

            StoreDynamicQueue dynamicQueue = new StoreDynamicQueue(storeCode2, numberOfTeamsWaiting, estimatedWaitingTime);
            dynamicQueues.add(dynamicQueue);
        }

        return dynamicQueues;
    }
}
