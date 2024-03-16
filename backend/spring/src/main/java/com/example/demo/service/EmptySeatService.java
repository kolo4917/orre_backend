package com.example.demo.service;

import com.example.demo.DTO.ToClient.EmptySeat;
import com.example.demo.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmptySeatService {

    private final RestaurantTableRepository restaurantTableRepository;

    @Autowired
    public EmptySeatService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    public List<EmptySeat> findEmptySeats(int storeCode) {
        // restaurantTableRepository를 사용하여 storeCode에 해당하는 모든 테이블 정보를 조회
        // 반환된 Object[] 각각은 테이블의 번호, 인원 수, 이용 가능 여부를 담고 있음
        List<Object[]> tables = restaurantTableRepository.findAvailableTablesByStoreCode(storeCode);

        // 조회된 테이블 정보(Object[])를 EmptySeat 객체로 변환
        return tables.stream()
                .map(object -> new EmptySeat((Integer)object[0], // 테이블 번호
                        (Integer)object[1], // 테이블 인원 수
                        (Integer)object[2])) // 테이블 이용 가능 여부
                .collect(Collectors.toList()); // 변환된 EmptySeat 객체들을 리스트로 모아 반환
    }
}
