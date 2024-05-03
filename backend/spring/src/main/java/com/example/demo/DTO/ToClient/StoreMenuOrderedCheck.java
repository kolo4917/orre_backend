package com.example.demo.DTO.ToClient;
import com.example.demo.DTO.ToClient.StoreMenuOrderedCheckOrderItem;

import java.util.List;

public class StoreMenuOrderedCheck {
    private String status;
    private int storeCode;
    private int tableNumber;
    private int totalPrice;
    private List<StoreMenuOrderedCheckOrderItem> orderItems;

    public StoreMenuOrderedCheck(String status, int storeCode, int tableNumber, int totalPrice, List<StoreMenuOrderedCheckOrderItem> orderItems) {
        this.status = status;
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<StoreMenuOrderedCheckOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<StoreMenuOrderedCheckOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
