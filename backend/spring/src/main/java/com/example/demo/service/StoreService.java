package com.example.demo.service;

import com.example.demo.DTO.ToClient.StoreDTO;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.MenuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;



@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuInfoRepository menuInfoRepository; // MenuInfoRepository 주입


    @Autowired
    public StoreService(StoreRepository storeRepository, MenuInfoRepository menuInfoRepository) {
        this.storeRepository = storeRepository;
        this.menuInfoRepository = menuInfoRepository;
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
                storeDTO.setNumberOfTeamsWaiting((Integer) innerArray[2]);
                storeDTO.setStoreInfoVersion((Integer) innerArray[3]);
                //예정시간 계산 알고리즘 아래 코드에서는 5분 가정
                storeDTO.setEstimatedWaitingTime(5*(Integer)innerArray[2]);
                storeDTO.setStoreImageMain((String) innerArray[4]);
                storeDTO.setStoreIntroduce((String) innerArray[5]);
                storeDTO.setStoreCategory((String) innerArray[6]);
                storeDTO.setOpeningTime((LocalTime) innerArray[7]);
                storeDTO.setClosingTime((LocalTime) innerArray[8]);
                storeDTO.setLastOrderTime((LocalTime) innerArray[9]);


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


                return storeDTO; // 첫 번째 상점 상세 정보에 대한 DTO 반환
            }
        }
        return null;
    }
}
