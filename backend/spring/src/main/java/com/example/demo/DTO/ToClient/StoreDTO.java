package com.example.demo.DTO.ToClient;

import java.time.LocalTime;
import java.util.List;

public class StoreDTO {

    private int storeCode;
    private String storeName;
    private int storeInfoVersion;
    private int numberOfTeamsWaiting;
    private int estimatedWaitingTime;
    private String storeImageMain;
    private String storeIntroduce;
    private String storeCategory;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalTime lastOrderTime;
    private List<MenuInfo> menuInfo;

    // 기본 생성자
    public StoreDTO() {
    }
    // 모든 필드를 포함한 생성자


    public StoreDTO(int storeCode, String storeName, int storeInfoVersion, int numberOfTeamsWaiting, int estimatedWaitingTime,
                    String storeImageMain, String storeIntroduce, String storeCategory,
                    LocalTime openingTime, LocalTime closingTime, LocalTime lastOrderTime,
                    List<MenuInfo> menuInfo) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.storeInfoVersion = storeInfoVersion;
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
        this.estimatedWaitingTime = estimatedWaitingTime;
        this.storeImageMain = storeImageMain;
        this.storeIntroduce = storeIntroduce;
        this.storeCategory = storeCategory;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.lastOrderTime = lastOrderTime;
        this.menuInfo = menuInfo;
    }

    // Getters
    public int getStoreCode() {
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

    public String getStoreImageMain() {return storeImageMain;}

    public String getStoreIntroduce() {return storeIntroduce;}

    public String getStoreCategory() {return storeCategory;}

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public LocalTime getLastOrderTime() {
        return lastOrderTime;
    }

    public List<MenuInfo> getMenuInfo() {
        return menuInfo;
    }

    // Setters
    public void setStoreCode(int storeCode) {
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

    public void setStoreImageMain(String storeImageMain) {this.storeImageMain = storeImageMain;}

    public void setStoreIntroduce(String storeIntroduce) {this.storeIntroduce = storeIntroduce;}

    public void setStoreCategory(String storeCategory) {this.storeCategory = storeCategory;}

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public void setLastOrderTime(LocalTime lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public void setMenuInfo(List<MenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }

    // 정적 중첩 클래스
    public static class MenuInfo {
        private String menu;
        private int price;
        private String menuCode;
        private int available;
        private int recommend;
        private String img;
        private String introduce;

        // 기본 생성자
        public MenuInfo() {
        }

        // 모든 필드를 포함한 생성자
        public MenuInfo(int storeCode, String menu, int price, String menuCode, int available, int amount,
                        int recommend, String img, String introduce) {
            this.menu = menu;
            this.price = price;
            this.menuCode = menuCode;
            this.available = available;
            this.recommend = recommend;
            this.img = img;
            this.introduce = introduce;
        }

        // 게터 메소드
        public String getMenu() {
            return menu;
        }

        public int getPrice() {
            return price;
        }

        public String getMenuCode() {
            return menuCode;
        }

        public int getAvailable() {
            return available;
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

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setMenuCode(String menuCode) {
            this.menuCode = menuCode;
        }

        public void setAvailable(int available) {
            this.available = available;
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
}
