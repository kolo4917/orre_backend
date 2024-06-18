package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "dankook_menu")
@IdClass(DankookMenuKey.class) // 복합 키 클래스 지정
public class DankookMenu {

    @Id
    @Column(name = "dankook_menu_date")
    private String date;

    @Id
    @Column(name = "dankook_menu_restaurant")
    private String restaurant;

    @Column(name = "breakfast", length = 1024)
    private String breakfast;

    @Column(name = "lunch", length = 1024)
    private String lunch;

    @Column(name = "dinner", length = 1024)
    private String dinner;

    public DankookMenu() {}

    public DankookMenu(String date, String restaurant, String breakfast, String lunch, String dinner) {
        this.date = date;
        this.restaurant = restaurant;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
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


class DankookMenuKey implements Serializable {
    private String date;
    private String restaurant;

    public DankookMenuKey() {}

    public DankookMenuKey(String date, String restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DankookMenuKey that = (DankookMenuKey) o;
        return Objects.equals(date, that.date) && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, restaurant);
    }
}
