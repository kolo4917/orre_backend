package com.example.demo.service;

import com.example.demo.DTO.ToServer.StoreMenuOrderRequest;
import com.example.demo.model.DataBase.MenuInfo;
import com.example.demo.repository.MenuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreMenuOrderService {

    private final MenuInfoRepository menuInfoRepository;

    @Autowired
    public StoreMenuOrderService(MenuInfoRepository menuInfoRepository) {
        this.menuInfoRepository = menuInfoRepository;
    }

    @Transactional
    public void ModifyMenuAmount(StoreMenuOrderRequest request) {
        int storeCode = request.getStoreCode();
        int tableNumber = request.getTableNumber();
        String menuCode = request.getMenuCode();
        int amountToAdd = request.getAmount();

        // MenuInfo 엔터티를 업데이트하는 쿼리 실행
        menuInfoRepository.updateAmountByStoreCodeAndTableNumberAndMenuCode(storeCode, tableNumber, menuCode, amountToAdd);
    }
    @Transactional
    public void ModifyMenuAmountForUser(StoreMenuOrderRequest request) {
        int storeCode = request.getStoreCode();
        int tableNumber = request.getTableNumber();
        String menuCode = request.getMenuCode();
        int amountToAdd = request.getAmount();
        if (amountToAdd <= 0) {
            throw new IllegalArgumentException("Amount to add must be greater than 0");
        }
        menuInfoRepository.updateAmountByStoreCodeAndTableNumberAndMenuCode(storeCode, tableNumber, menuCode, amountToAdd);

    }
}
