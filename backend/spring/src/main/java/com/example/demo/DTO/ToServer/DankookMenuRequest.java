package com.example.demo.DTO.ToServer;

import java.util.Date;

public class DankookMenuRequest {
    private String restaurantLocation;
    private String date; //06월17일 이런 양식

    public DankookMenuRequest(String restaurantLocation, String date) {
        this.restaurantLocation = restaurantLocation;
        this.date = date;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
