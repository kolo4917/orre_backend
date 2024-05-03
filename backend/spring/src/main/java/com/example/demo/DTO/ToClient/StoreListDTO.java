package com.example.demo.DTO.ToClient;
import java.time.LocalTime;


// LOCATION_INFO 테이블 정보를 담고 비교해서 클라이언트에게 보내줄 정보
public class StoreListDTO implements Comparable<StoreListDTO> {
    private int storeCode;
    private String storeName;
    private String address;
    private double distance;
    private double latitude; // Latitude field 추가
    private double longitude; // Longitude field 추가
    private String storeImageMain;
    private String storeShortIntroduce;
    private String storeCategory;
    private int storeInfoVersion;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalTime lastOrderTime;


    public StoreListDTO(int storeCode, String storeName, String address, double distance, double latitude, double longitude,
                        String storeImageMain, String storeShortIntroduce, String storeCategory, int storeInfoVersion,
                        LocalTime openingTime, LocalTime closingTime, LocalTime lastOrderTime) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.address = address;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storeImageMain = storeImageMain;
        this.storeShortIntroduce = storeShortIntroduce;
        this.storeCategory = storeCategory;
        this.storeInfoVersion = storeInfoVersion;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.lastOrderTime = lastOrderTime;
    }

    // ID에 대한 getter
    public int getStoreCode() {
        return storeCode;
    }

    // ID에 대한 setter
    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    // StoreName에 대한 getter
    public String getStoreName() {
        return storeName;
    }

    // StoreName에 대한 setter
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    // Address에 대한 getter
    public String getAddress() {
        return address;
    }

    // Address에 대한 setter
    public void setAddress(String address) {
        this.address = address;
    }

    // Distance에 대한 getter
    public double getDistance() {
        return distance;
    }

    // Distance에 대한 setter
    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Latitude에 대한 getter
    public double getLatitude() {
        return latitude;
    }

    // Latitude에 대한 setter
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Longitude에 대한 getter
    public double getLongitude() {
        return longitude;
    }

    // Longitude에 대한 setter
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStoreImageMain() {return storeImageMain;}

    public void setStoreImageMain(String storeImageMain) {this.storeImageMain = storeImageMain;}

    public String getStoreShortIntroduce() {return storeShortIntroduce;}

    public void setStoreShortIntroduce(String storeShortIntroduce) {this.storeShortIntroduce = storeShortIntroduce;}

    public String getStoreCategory() {return storeCategory;}

    public void setStoreCategory(String storeCategory) {this.storeCategory = storeCategory;}

    public int getStoreInfoVersion() {
        return storeInfoVersion;
    }

    public void setStoreInfoVersion(int storeInfoVersion) {
        this.storeInfoVersion = storeInfoVersion;
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

    // Comparable 인터페이스의 compareTo 메소드 구현
    @Override
    public int compareTo(StoreListDTO other) {
        return Double.compare(this.distance, other.distance);
    }
}
