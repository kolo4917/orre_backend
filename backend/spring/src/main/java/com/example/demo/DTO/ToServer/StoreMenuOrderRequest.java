package com.example.demo.DTO.ToServer;

public class StoreMenuOrderRequest {
    private int storeCode;
    private int tableNumber;
    private String jwt;
    private int amount;
    private String menuCode;

    public StoreMenuOrderRequest(int storeCode, int tableNumber, String jwt, int amount, String menuCode) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.jwt = jwt;
        this.amount = amount;
        this.menuCode = menuCode;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
