package com.example.demo.service;

import com.example.demo.DTO.ToServer.TableAddRequest;
import com.example.demo.model.DataBase.RestaurantTable;
import com.example.demo.repository.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.DTO.ToClient.BooleanResponse;

@Service
public class TableFixService {

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Transactional
    public BooleanResponse addTable(TableAddRequest request) {
        try {
            // DTO에서 데이터 추출
            Integer storeCode = request.getStoreCode();
            Integer tableNumber = request.getStoreAddTableNumber();
            Integer personNumber = request.getPersonNumber();

            Integer tableAvailable = 0; // 0은 이용 가능, 1은 이용 불가능
            String tablePhoneNumber = ""; // 초기에는 전화번호 X

            // 새 RestaurantTable 엔티티 생성
            RestaurantTable newTable = new RestaurantTable();
            newTable.setStoreCode(storeCode);
            newTable.setTableNumber(tableNumber);
            newTable.setTablePersonNumber(personNumber);
            newTable.setTableAvailable(tableAvailable);
            newTable.setTablePhoneNumber(tablePhoneNumber);

            // 데이터베이스에 저장
            restaurantTableRepository.save(newTable);

            return new BooleanResponse(true); // 성공 시 true 반환
        } catch (Exception e) {
            // 데이터베이스 저장 실패 시 예외 처리
            return new BooleanResponse(false); // 실패 시 false 반환
        }
    }
}
