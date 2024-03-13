package com.example.demo.DTO;

import java.util.List;

public class StoreDTO {

    private String storeCode;
    private String storeName;
    private int storeInfoVersion;
    private int numberOfTeamsWaiting;
    private int estimatedWaitingTime;
    private List<MenuInfo> menuInfo;

    // Getters
    public String getStoreCode() {
        return storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getStoreInfoVersion() {
        return storeInfoVersion;
    }

    public int getNumberOfTeamsWaiting() {
        return numberOfTeamsWaiting;
    }

    public int getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public List<MenuInfo> getMenuInfo() {
        return menuInfo;
    }

    // Setters
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreInfoVersion(int storeInfoVersion) {
        this.storeInfoVersion = storeInfoVersion;
    }

    public void setNumberOfTeamsWaiting(int numberOfTeamsWaiting) {
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
    }

    public void setEstimatedWaitingTime(int estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public void setMenuInfo(List<MenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }
}

class MenuInfo {
    private int storeCode;
    private String menu;
    private int price;
    private int amount;
    private int recommend;
    private String img;
    private String introduce;

    // 기본 생성자
    public MenuInfo() {
    }

    // 모든 필드를 포함한 생성자
    public MenuInfo(int storeCode, String menu, int price, int amount, int recommend, String img, String introduce) {
        this.storeCode = storeCode;
        this.menu = menu;
        this.price = price;
        this.amount = amount;
        this.recommend = recommend;
        this.img = img;
        this.introduce = introduce;
    }

    // 게터 메소드
    public int getStoreCode() {
        return storeCode;
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getRecommend() {
        return recommend;
    }

    public String getImg() {
        return img;
    }

    public String getIntroduce() {
        return introduce;
    }

    // 세터 메소드
    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
