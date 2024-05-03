package com.example.demo.service;

import com.example.demo.DTO.ToClient.StoreMenuOrderedCheck;
import com.example.demo.DTO.ToClient.StoreMenuOrderedCheckOrderItem;
import com.example.demo.DTO.ToServer.StoreMenuOrderedCheckRequest;
import com.example.demo.repository.MenuInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreMenuOrderedCheckService {

    private final MenuInfoRepository menuInfoRepository;

    @Autowired
    public StoreMenuOrderedCheckService(MenuInfoRepository menuInfoRepository) {
        this.menuInfoRepository = menuInfoRepository;
    }

    public StoreMenuOrderedCheck getStoreMenuOrderedCheck(StoreMenuOrderedCheckRequest request) {
        int storeCode = request.getStoreCode();
        int tableNumber = request.getTableNumber();
        int amount = 0;

        List<Object[]> menuDetails = menuInfoRepository.findMenuDetailsByStoreCodeAndTableNumber(storeCode, tableNumber, amount);
        int totalPrice = 0;
        List<StoreMenuOrderedCheckOrderItem> orderItems = new ArrayList<>();

        for (Object[] menuDetail : menuDetails) {
            String menuName = (String) menuDetail[0];
            int price = (Integer) menuDetail[1];
            int quantity = (Integer) menuDetail[2];
            totalPrice += price * quantity;
            StoreMenuOrderedCheckOrderItem orderItem = new StoreMenuOrderedCheckOrderItem(menuName, price, quantity);
            orderItems.add(orderItem);
        }

        return new StoreMenuOrderedCheck("200",storeCode, tableNumber, totalPrice, orderItems);
    }
}
