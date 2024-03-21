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

        // 데이터가 비어 있는지 확인
        if (dynamicQueueData.isEmpty()) {
            // 처리할 데이터가 없으므로 빈 리스트를 반환
            return dynamicQueues;
        }

        // 대기 팀 리스트를 초기화합니다.
        List<Integer> waitingTeamList = new ArrayList<>();

        // 조회된 데이터를 기반으로 대기 팀 리스트를 채웁니다.
        for (Object[] data : dynamicQueueData) {
            waitingTeamList.add((int) data[1]);
        }

        // 상위 3개 팀을 입장 팀 리스트에 추가합니다. // 가게가 부르는 매커니즘으로 해도 좋을 듯
        List<Integer> enteringTeamList = new ArrayList<>();
        for (int i = 0; i < Math.min(waitingTeamList.size(), 3); i++) {
            enteringTeamList.add(waitingTeamList.get(i));
        }

        // 조회된 데이터의 첫 번째 데이터를 사용하여 가게 코드를 가져옵니다.(한번만 가져와도 됨)
        int dynamicstoreCode = (int) dynamicQueueData.get(0)[0];

        // 예상 대기 시간을 계산
        int estimatedWaitingTimePerTeam = 5; // 대기 시간 (팀당) //ML로 통계 쌓이면 구현 예정

        // StoreDynamicQueue 객체를 생성하고 반환합니다.
        StoreDynamicQueue dynamicQueue = new StoreDynamicQueue(dynamicstoreCode, waitingTeamList, enteringTeamList, estimatedWaitingTimePerTeam);
        dynamicQueues.add(dynamicQueue);

        return dynamicQueues;
    }
}
