package com.example.demo.service;

import com.example.demo.repository.StoreDynamicQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.DTO.ToClient.StoreDynamicQueue;
import com.example.demo.DTO.ToClient.ExtendedStoreDynamicQueue;

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

        // 대기 팀 리스트, 전화번호 리스트, 인원수 리스트를 초기화합니다.
        List<Integer> waitingTeamList = new ArrayList<>();
        List<String> phoneNumberList = new ArrayList<>();
        List<Integer> personNumberList = new ArrayList<>();
        List<Integer> enteringTeamList = new ArrayList<>();

        // 데이터가 비어 있는지 확인
        if (dynamicQueueData.isEmpty()) {
            // 데이터가 비어 있으면 storeCode만 반환하고 나머지 값들은 null로 설정
            return new ExtendedStoreDynamicQueue(storeCode, waitingTeamList, enteringTeamList, phoneNumberList, personNumberList, 5);
        }

// 조회된 데이터를 기반으로 대기 팀 리스트, 폰 번호 리스트, 인원수 리스트를 채웁니다.
        for (Object[] data : dynamicQueueData) {
            waitingTeamList.add((Integer) data[1]);
            phoneNumberList.add((String) data[3]);  // 폰 번호 추가
            personNumberList.add((Integer) data[4]); // 인원수 추가
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
        ExtendedStoreDynamicQueue dynamicQueue = new ExtendedStoreDynamicQueue(dynamicstoreCode, waitingTeamList, enteringTeamList, phoneNumberList, personNumberList, estimatedWaitingTimePerTeam);

        return dynamicQueue;
    }
}
