package com.example.demo.service;

import com.example.demo.repository.StoreDynamicQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueue;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueueTeamInfo;

@Service
public class StoreDynamicQueueService {

    private final StoreDynamicQueueRepository storeDynamicQueueRepository;

    @Autowired
    public StoreDynamicQueueService(StoreDynamicQueueRepository storeDynamicQueueRepository) {
        this.storeDynamicQueueRepository = storeDynamicQueueRepository;
    }

    public StoreDynamicQueue findStoreDynamicQueue(Integer storeCode) {
        // 레포지토리를 통해 가게의 동적 대기열 정보를 조회
        List<Object[]> dynamicQueueData = storeDynamicQueueRepository.findStoreDynamicQueue(storeCode);

        // 대기 팀 리스트를 초기화합니다.
        List<Integer> waitingTeamList = new ArrayList<>();
        List<Integer> enteringTeamList = new ArrayList<>();

        // 데이터가 비어 있는지 확인
        if (dynamicQueueData.isEmpty()) {
            // 데이터가 비어 있으면 storeCode만 반환하고 나머지 값들은 null로 설정
            return new StoreDynamicQueue(storeCode, waitingTeamList, enteringTeamList, 5);
        }

        // 조회된 데이터를 기반으로 대기 팀 리스트를 채웁니다.
        for (Object[] data : dynamicQueueData) {
            waitingTeamList.add((Integer) data[1]);
        }

        // 상위 3개 팀을 입장 팀 리스트에 추가합니다.
        for (int i = 0; i < Math.min(waitingTeamList.size(), 3); i++) {
            enteringTeamList.add(waitingTeamList.get(i));
        }

        // 조회된 데이터의 첫 번째 데이터를 사용하여 가게 코드를 가져옵니다.(한번만 가져와도 됨)
        int dynamicstoreCode = (Integer) dynamicQueueData.get(0)[0];

        // 예상 대기 시간을 계산
        int estimatedWaitingTimePerTeam = 5; // 대기 시간 (팀당) //ML로 통계 쌓이면 구현 예정

        // StoreDynamicQueue 객체를 생성하고 반환합니다.
        StoreDynamicQueue dynamicQueue = new StoreDynamicQueue(dynamicstoreCode, waitingTeamList, enteringTeamList, estimatedWaitingTimePerTeam);

        return dynamicQueue;
    }

    public ExtendedStoreDynamicQueue findStoreByExtendedQueue(Integer storeCode) {
        // 레포지토리를 통해 가게의 동적 대기열 정보를 조회
        List<Object[]> dynamicQueueData = storeDynamicQueueRepository.findStoreByExtendedQueue(storeCode);

        // ExtendedStoreDynamicQueueTeamInfo 객체의 리스트를 초기화합니다.
        List<ExtendedStoreDynamicQueueTeamInfo> teamInfoList = new ArrayList<>();

        // 데이터가 비어 있는지 확인
        if (dynamicQueueData.isEmpty()) {
            // 데이터가 비어 있으면 storeCode만 반환하고 나머지 값들은 빈 리스트로 설정
            return new ExtendedStoreDynamicQueue(storeCode, teamInfoList, 5);
        }

        // 조회된 데이터를 기반으로 teamInfoList를 채웁니다.
        for (Object[] data : dynamicQueueData) {
            Integer waitingTeam = (Integer) data[1];
            Integer enteringTeam = (Integer) data[2]; // 가정: 입장 팀 번호도 데이터로부터 얻는다.
            String phoneNumber = (String) data[3];
            Integer personNumber = (Integer) data[4];
            teamInfoList.add(new ExtendedStoreDynamicQueueTeamInfo(waitingTeam, enteringTeam, phoneNumber, personNumber));
        }

        // 예상 대기 시간을 계산
        int estimatedWaitingTimePerTeam = 5; // 대기 시간 (팀당) //ML로 통계 쌓이면 구현 예정

        // 조회된 데이터의 첫 번째 데이터를 사용하여 가게 코드를 가져옵니다.(한번만 가져와도 됨)
        int dynamicStoreCode = (Integer) dynamicQueueData.get(0)[0];

        // ExtendedStoreDynamicQueue 객체를 생성하고 반환합니다.
        ExtendedStoreDynamicQueue dynamicQueue = new ExtendedStoreDynamicQueue(dynamicStoreCode, teamInfoList, estimatedWaitingTimePerTeam);

        return dynamicQueue;
    }
}
