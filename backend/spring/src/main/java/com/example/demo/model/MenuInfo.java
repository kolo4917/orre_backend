package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Entity
@Table(name = "menu_info")
public class MenuInfo implements Serializable {

    @Id
    @Column(name = "menu_info_store_code")
    private Integer storeCode;

    @Id
    @Column(name = "menu_info_table_number")
    private Integer tableNumber;

    @Id
    @Column(name = "menu_info_menu")
    private String menu;

    @Column(name = "menu_info_price")
    private Integer price;

    @Column(name = "menu_info_amount")
    private Integer amount;

    @Column(name = "menu_info_recommend")
    private Integer recommend;

    @Column(name = "menu_info_img")
    private String img;

    @Column(name = "menu_info_introduce")
    private String introduce;

    @OneToOne
    @JoinColumn(name = "menu_info_store_code", referencedColumnName = "store_code", insertable = false, updatable = false)
    @JoinColumn(name = "menu_info_table_number", referencedColumnName = "table_number", insertable = false, updatable = false)
    private RestaurantTable restaurantTable;

    public MenuInfo() {}

    public MenuInfo(Integer storeCode, Integer tableNumber, String menu, Integer price, Integer amount, Integer recommend, String img, String introduce, RestaurantTable restaurantTable) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.menu = menu;
        this.price = price;
        this.amount = amount;
        this.recommend = recommend;
        this.img = img;
        this.introduce = introduce;
        this.restaurantTable = restaurantTable;
    }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public String getMenu() { return menu; }
    public void setMenu(String menu) { this.menu = menu; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public Integer getRecommend() { return recommend; }
    public void setRecommend(Integer recommend) { this.recommend = recommend; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getIntroduce() { return introduce; }
    public void setIntroduce(String introduce) { this.introduce = introduce; }

    public RestaurantTable getRestaurantTable() { return restaurantTable; }
    public void setRestaurantTable(RestaurantTable restaurantTable) { this.restaurantTable = restaurantTable; }

    // toString, equals, hashCode 메소드는 필요에 따라 구현
}
