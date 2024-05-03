package com.example.demo.DTO.ToClient;

public class StoreMenuOrderedCheckOrderItem {
    private String menuName;
    private int price;
    private int amount;

    public StoreMenuOrderedCheckOrderItem(String menuName, int price, int amount) {
        this.menuName = menuName;
        this.price = price;
        this.amount = amount;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
