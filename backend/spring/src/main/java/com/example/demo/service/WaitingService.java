package com.example.demo.service;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.example.demo.DTO.StoreDistanceDTO;
import com.example.demo.model.LocationData;
import com.example.demo.model.StoreInfo;
import com.example.demo.model.WaitingTable;
import com.example.demo.repository.WaitingTableRepository;
import com.example.demo.repository.StoreInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitingService {

    @Autowired
    private WaitingTableRepository waitingTableRepository;
    @Autowired
    private StoreInfoRepository storeInfoRepository;

    public WaitingTable registerWaiting(WaitingTable waitingTable) {
        // 웨이팅 정보 등록 로직
        return waitingTableRepository.save(waitingTable);
    }

    // 스토어 정보 가져와서 현재 위치와 비교
    public List<StoreDistanceDTO> findNearestStores(LocationData locationData) {
        List<StoreInfo> allStores = storeInfoRepository.findAll(); // 테이블의 모든 정보를 가져옴
        List<StoreDistanceDTO> nearestStoresWithDistance = new ArrayList<>();

        for (StoreInfo store : allStores) {
            double distance = calculateDistance(locationData.getLatitude(), locationData.getLongitude(), //클라이언트로 부터 받은 위도 경도
                    store.getLatitude(), store.getLongitude()); // 테이블 한 행에 있는 위도 경도
            nearestStoresWithDistance.add(new StoreDistanceDTO(store.getId(), store.getStoreName(), store.getAddress(), distance,store.getLatitude(), store.getLongitude())); //distance를 추가해서 저장
        }

        // distance 따라 정렬
        Collections.sort(nearestStoresWithDistance);

        return nearestStoresWithDistance;
    }

    // Haversine 공식을 사용하여 두 지점 간의 거리 계산
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구의 반경(km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c * 1000; // 거리를 미터로 변환
        long meter_distance = Math.round(distance); // 소수점 없이 반올림

        System.out.println(distance + " meters");

        return meter_distance;
    }
}