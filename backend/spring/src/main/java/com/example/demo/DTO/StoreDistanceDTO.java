package com.example.demo.DTO;

// LOCATION_INFO 테이블 정보를 담고 비교해서 클라이언트에게 보내줄 정보
public class StoreDistanceDTO implements Comparable<StoreDistanceDTO> {
    private Long id;
    private String storeName;
    private String address;
    private double distance;
    private double latitude; // Latitude field 추가
    private double longitude; // Longitude field 추가

    public StoreDistanceDTO(Long id, String storeName, String address, double distance, double latitude, double longitude) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.distance = distance;
        this.latitude = latitude; // Constructor에서 latitude 초기화
        this.longitude = longitude; // Constructor에서 longitude 초기화
    }

    // ID에 대한 getter
    public Long getId() {
        return id;
    }

    // ID에 대한 setter
    public void setId(Long id) {
        this.id = id;
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

    // Comparable 인터페이스의 compareTo 메소드 구현
    @Override
    public int compareTo(StoreDistanceDTO other) {
        return Double.compare(this.distance, other.distance);
    }
}
