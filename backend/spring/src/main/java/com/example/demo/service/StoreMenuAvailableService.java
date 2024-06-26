package com.example.demo.service;

import com.example.demo.DTO.ToServer.StoreMenuAvailableRequest;
import com.example.demo.config.events.StoreQueueUpdatedEvent;
import com.example.demo.model.DataBase.MenuInfo;
import com.example.demo.repository.MenuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.config.events.EventPublisherService;
import org.springframework.context.ApplicationEventPublisher;
import com.example.demo.config.events.StoreInfoUpdatedEvent;

import java.util.List;

@Service
public class StoreMenuAvailableService {

    private final MenuInfoRepository menuInfoRepository;
    @Autowired
    private EventPublisherService eventPublisherService;

    @Autowired
    public StoreMenuAvailableService(MenuInfoRepository menuInfoRepository) {
        this.menuInfoRepository = menuInfoRepository;
    }

    public void updateMenuAvailability(StoreMenuAvailableRequest request) {
        String menuCode = request.getMenuCode();

        // MenuCode가 '*'이면 해당 StoreCode를 가진 모든 테이블의 가용성을 업데이트
        if (menuCode.equals("*")) {
            updateAllTablesAvailability(request.getStoreCode(), request.getAvailableCode());
        }

        // MenuCode가 알파벳 하나만 포함된 경우
        if (menuCode.matches("[A-Z]")) {
            // 해당 그룹에 속하는 모든 메뉴의 가용성을 업데이트
            menuInfoRepository.updateAvailableByStoreCodeAndMenuCodeStartingWith(request.getStoreCode(), menuCode, request.getAvailableCode());
        }
        else {
            // 해당 메뉴의 가용성을 업데이트
            if (!menuCode.equals("")) {
                menuInfoRepository.updateAvailableByStoreCodeAndMenuCode(request.getStoreCode(), menuCode, request.getAvailableCode());
            }
        }
        // 이벤트 발생시키는 메소드
        eventPublisherService.publishStoreInfoUpdate(new StoreInfoUpdatedEvent(this, request.getStoreCode()),1000);

    }

    private void updateAllTablesAvailability(Integer storeCode, Integer availableCode) {
        // 해당 StoreCode를 가진 모든 테이블의 메뉴 가용성을 업데이트
        menuInfoRepository.updateAvailableByStoreCode(storeCode, availableCode);
    }
}
