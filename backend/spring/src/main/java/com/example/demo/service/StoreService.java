package com.example.demo.service;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.model.DataBase.MenuCategory;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.MenuInfoRepository;
import com.example.demo.repository.StoreInfoRepository;
import com.example.demo.repository.MenuCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;



@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuInfoRepository menuInfoRepository; // MenuInfoRepository 주입
    private final StoreInfoRepository storeInfoRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, MenuInfoRepository menuInfoRepository, StoreInfoRepository storeInfoRepository, MenuCategoryRepository menuCategoryRepository) {
        this.storeRepository = storeRepository;
        this.menuInfoRepository = menuInfoRepository;
        this.storeInfoRepository = storeInfoRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    public StoreDTO getStoreDetailsByStoreCode(Integer storeCode) {
        Object[] storeDetails = storeRepository.findStoreDetailsByStoreCode(storeCode);
        if (storeDetails != null && storeDetails.length > 0) {
            Object firstElement = storeDetails[0];
            if (firstElement instanceof Object[]) {
                // 이중 배열인 경우
                Object[] innerArray = (Object[]) firstElement;
                StoreDTO storeDTO = new StoreDTO();
                // 쿼리 결과를 StoreDTO 필드에 매핑
                storeDTO.setStoreCode((Integer) innerArray[0]); // storeCode를 String으로 변환
                storeDTO.setStoreName((String) innerArray[1]);
                storeDTO.setStorePhoneNumber((String) innerArray[2]);
                storeDTO.setNumberOfTeamsWaiting((Integer) innerArray[3]);
                storeDTO.setWaitingAvailable((Integer) innerArray[4]);
                storeDTO.setStoreInfoVersion((Integer) innerArray[5]);
                //예정시간 계산 알고리즘 아래 코드에서는 5분 가정
                storeDTO.setEstimatedWaitingTime(5*(Integer)innerArray[3]);
                storeDTO.setStoreImageMain((String) innerArray[6]);
                storeDTO.setStoreIntroduce((String) innerArray[7]);
                storeDTO.setStoreCategory((String) innerArray[8]);
                storeDTO.setOpeningTime((LocalTime) innerArray[9]);
                storeDTO.setClosingTime((LocalTime) innerArray[10]);
                storeDTO.setLastOrderTime((LocalTime) innerArray[11]);
                storeDTO.setStartBreakTime((LocalTime) innerArray[12]);
                storeDTO.setEndBreakTime((LocalTime) innerArray[13]);



                // 메뉴 정보 조회 및 설정
                List<Object[]> menuDetails = menuInfoRepository.findMenuDetailsByStoreCodeAndTableNumber(storeCode);
                List<StoreDTO.MenuInfo> menuInfos = new ArrayList<>(); //
                for (Object[] menuDetail : menuDetails) {
                    StoreDTO.MenuInfo menuInfo = new StoreDTO.MenuInfo();
                    menuInfo.setMenu((String) menuDetail[0]);
                    menuInfo.setPrice((Integer) menuDetail[1]);
                    menuInfo.setMenuCode((String) menuDetail[2]);
                    menuInfo.setAvailable((Integer) menuDetail[3]);
                    menuInfo.setRecommend((Integer) menuDetail[4]);
                    menuInfo.setImg((String) menuDetail[5]);
                    menuInfo.setIntroduce((String) menuDetail[6]);
                    menuInfos.add(menuInfo);
                }
                storeDTO.setMenuInfo(menuInfos);

                // 위치 정보를 조회하는 쿼리 메서드 추가
                List<Object[]> locationDetails = storeInfoRepository.findLocationDetailsByStoreCode(storeCode);
                List<StoreDTO.LocationInfo> locationInfos = new ArrayList<>();
                for (Object[] locationDetail : locationDetails) {
                    StoreDTO.LocationInfo locationInfo = new StoreDTO.LocationInfo();
                    locationInfo.setStoreName((String) locationDetail[0]);
                    locationInfo.setLatitude((Double) locationDetail[1]);
                    locationInfo.setLongitude((Double) locationDetail[2]);
                    locationInfo.setAddress((String) locationDetail[3]);
                    locationInfos.add(locationInfo);
                }
                storeDTO.setLocationInfo(locationInfos);
                MenuCategory menuCategory = menuCategoryRepository.findByStoreCode(storeCode);
                if (menuCategory != null) {
                    storeDTO.setMenuCategories(menuCategory);
                }



                return storeDTO; // 첫 번째 상점 상세 정보에 대한 DTO 반환
            }
        }
        return null;
    }
}
