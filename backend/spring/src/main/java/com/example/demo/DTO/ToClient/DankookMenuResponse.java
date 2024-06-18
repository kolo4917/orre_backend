package com.example.demo.DTO.ToClient;

public class DankookMenuResponse {

    private String restaurantLocation;
    private String date; //06월17일 이런 양식
    private String breakfast;
    private String lunch;
    private String dinner;

    public DankookMenuResponse(String restaurantLocation, String date, String breakfast, String lunch, String dinner) {
        this.restaurantLocation = restaurantLocation;
        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
    public DankookMenuResponse() {}

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

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
