package com.example.demo.DTO.ToClient;

import java.time.LocalTime;
import java.util.List;

public class StoreDTO {

    private int storeCode;
    private String storeName;
    private String storePhoneNumber;
    private int storeInfoVersion;
    private int waitingAvailable;
    private int numberOfTeamsWaiting;
    private int estimatedWaitingTime;
    private String storeImageMain;
    private String storeIntroduce;
    private String storeCategory;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalTime lastOrderTime;
    private LocalTime startBreakTime;
    private LocalTime endBreakTime;
    private List<MenuInfo> menuInfo;
    private List<LocationInfo> locationInfo;

    // 기본 생성자
    public StoreDTO() {
    }
    // 모든 필드를 포함한 생성자


    public StoreDTO(int storeCode, String storeName, String storePhoneNumber, int storeInfoVersion, int waitingAvailable,
                    int numberOfTeamsWaiting, int estimatedWaitingTime, String storeImageMain, String storeIntroduce, String storeCategory,
                    LocalTime openingTime, LocalTime closingTime, LocalTime lastOrderTime, LocalTime startBreakTime, LocalTime endBreakTime,
                    List<MenuInfo> menuInfo, List<LocationInfo> locationInfo) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.storePhoneNumber = storePhoneNumber;
        this.storeInfoVersion = storeInfoVersion;
        this.waitingAvailable = waitingAvailable;
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
        this.estimatedWaitingTime = estimatedWaitingTime;
        this.storeImageMain = storeImageMain;
        this.storeIntroduce = storeIntroduce;
        this.storeCategory = storeCategory;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.lastOrderTime = lastOrderTime;
        this.startBreakTime = startBreakTime;
        this.endBreakTime = endBreakTime;
        this.menuInfo = menuInfo;
        this.locationInfo = locationInfo;
    }

    // Getters


    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhoneNumber() {
        return storePhoneNumber;
    }

    public void setStorePhoneNumber(String storePhoneNumber) {
        this.storePhoneNumber = storePhoneNumber;
    }

    public int getStoreInfoVersion() {
        return storeInfoVersion;
    }

    public void setStoreInfoVersion(int storeInfoVersion) {
        this.storeInfoVersion = storeInfoVersion;
    }

    public int getWaitingAvailable() {
        return waitingAvailable;
    }

    public void setWaitingAvailable(int waitingAvailable) {
        this.waitingAvailable = waitingAvailable;
    }

    public int getNumberOfTeamsWaiting() {
        return numberOfTeamsWaiting;
    }

    public void setNumberOfTeamsWaiting(int numberOfTeamsWaiting) {
        this.numberOfTeamsWaiting = numberOfTeamsWaiting;
    }

    public int getEstimatedWaitingTime() {
        return estimatedWaitingTime;
    }

    public void setEstimatedWaitingTime(int estimatedWaitingTime) {
        this.estimatedWaitingTime = estimatedWaitingTime;
    }

    public String getStoreImageMain() {
        return storeImageMain;
    }

    public void setStoreImageMain(String storeImageMain) {
        this.storeImageMain = storeImageMain;
    }

    public String getStoreIntroduce() {
        return storeIntroduce;
    }

    public void setStoreIntroduce(String storeIntroduce) {
        this.storeIntroduce = storeIntroduce;
    }

    public String getStoreCategory() {
        return storeCategory;
    }

    public void setStoreCategory(String storeCategory) {
        this.storeCategory = storeCategory;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public LocalTime getLastOrderTime() {
        return lastOrderTime;
    }

    public void setLastOrderTime(LocalTime lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public LocalTime getStartBreakTime() {
        return startBreakTime;
    }

    public void setStartBreakTime(LocalTime startBreakTime) {
        this.startBreakTime = startBreakTime;
    }

    public LocalTime getEndBreakTime() {
        return endBreakTime;
    }

    public void setEndBreakTime(LocalTime endBreakTime) {
        this.endBreakTime = endBreakTime;
    }

    public List<MenuInfo> getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(List<MenuInfo> menuInfo) {
        this.menuInfo = menuInfo;
    }

    public List<LocationInfo> getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(List<LocationInfo> locationInfo) {
        this.locationInfo = locationInfo;
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
    public static class LocationInfo {
        public LocationInfo(){}
        private String storeName;
        private double latitude;
        private double longitude;
        private String address;

        public LocationInfo(String storeName, double latitude, double longitude, String address) {
            this.storeName = storeName;
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
