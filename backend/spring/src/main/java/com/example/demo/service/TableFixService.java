package com.example.demo.service;

import com.example.demo.DTO.ToServer.TableAddRequest;
import com.example.demo.DTO.ToServer.TableRemoveRequest;
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
    @Transactional
    public BooleanResponse removeTable(TableRemoveRequest request) {
        try {
            Integer storeCode = request.getStoreCode();
            Integer tableNumber = request.getStoreRemoveTableNumber();
            // 데이터베이스에서 해당 스토어 코드와 테이블 번호로 테이블 찾기
            RestaurantTable existingTable = restaurantTableRepository.findByStoreCodeAndTableNumber(storeCode, tableNumber);

            // 테이블이 존재하지 않는 경우
            if (existingTable == null) {
                return new BooleanResponse(false); // 없는 테이블이므로 실패 반환
            }

            // 존재하는 테이블 삭제
            restaurantTableRepository.delete(existingTable);
            return new BooleanResponse(true); // 성공적으로 삭제되면 true 반환
        } catch (Exception e) {
            // 삭제 중 예외 발생 시
            return new BooleanResponse(false); // 실패 시 false 반환
        }
    }
}
