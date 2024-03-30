package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "LOCATION_INFO") // 소문자로 변환되서 테이블에 전달됨 MYSQL의 경우 구분하기 때문에 골치 아픔
public class StoreInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_store_code")
    private int storeCode;

    @Column(name = "location_store_name")
    private String storeName;

    @Column(name = "location_info_address")
    private String address;

    @Column(name = "location_info_longitude")
    private Double longitude;

    @Column(name = "location_info_latitude")
    private Double latitude;
    @Column(name = "location_store_image_main")
    private String storeImageMain;
    @Column(name = "location_store_short_introduce")
    private String storeShortIntroduce;
    @Column(name = "location_store_category")
    private String storeCategory;
    // 기본 생성자
    public StoreInfo() {
    }

    // 모든 필드를 포함하는 생성자
    public StoreInfo(int storeCode, String storeName, String address, Double longitude, Double latitude, String storeImageMain, String storeShortIntroduce, String storeCategory) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.storeImageMain = storeImageMain;
        this.storeShortIntroduce = storeShortIntroduce;
        this.storeCategory = storeCategory;
    }

    public int getstoreCode() {return storeCode;}

    public void setstoreCode(int storeCode) {this.storeCode = storeCode;}

    public String getStoreName() {return storeName;}

    public void setStoreName(String storeName) {this.storeName = storeName;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public Double getLongitude() {return longitude;}

    public void setLongitude(Double longitude) {this.longitude = longitude;}

    public Double getLatitude() {return latitude;}

    public void setLatitude(Double latitude) {this.latitude = latitude;}

    public String getStoreImageMain() {return storeImageMain;}

    public void setStoreImageMain(String storeImageMain) {this.storeImageMain = storeImageMain;}

    public String getStoreShortIntroduce() {return storeShortIntroduce;}

    public void setStoreShortIntroduce(String storeShortIntroduce) {this.storeShortIntroduce = storeShortIntroduce;}

    public String getStoreCategory() {return storeCategory;}

    public void setStoreCategory(String storeCategory) {this.storeCategory = storeCategory;}
}
