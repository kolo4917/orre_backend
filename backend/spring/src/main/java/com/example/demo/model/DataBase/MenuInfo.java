package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

import java.io.Serializable;

@Entity
@Table(name = "menu_info")
@IdClass(MenuInfoKey.class) // 복합 키 클래스 지정
public class MenuInfo{

    @Id
    @Column(name = "menu_info_store_code")
    private Integer storeCode;
    @Id
    @Column(name = "menu_info_table_number")
    private Integer tableNumber;
    @Column(name = "menu_info_menu")
    private String menu;

    @Column(name = "menu_info_price")
    private Integer price;

    @Column(name = "menu_info_amount")
    private Integer amount;
    @Id
    @Column(name = "menu_info_menu_code")
    private String menuCode;
    @Column(name = "menu_info_available")
    private Integer available;

    @Column(name = "menu_info_recommend")
    private Integer recommend;

    @Column(name = "menu_info_img")
    private String img;

    @Column(name = "menu_info_introduce")
    private String introduce;


    public MenuInfo() {}

    public MenuInfo(Integer storeCode, Integer tableNumber, String menu, Integer price, Integer amount, String menuCode, Integer available , Integer recommend, String img, String introduce) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.menu = menu;
        this.price = price;
        this.amount = amount;
        this.menuCode = menuCode;
        this.available = available;
        this.recommend = recommend;
        this.img = img;
        this.introduce = introduce;
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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getRecommend() { return recommend; }
    public void setRecommend(Integer recommend) { this.recommend = recommend; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getIntroduce() { return introduce; }
    public void setIntroduce(String introduce) { this.introduce = introduce; }

}
// 복합 기본 키 클래스를 정의합니다.
class MenuInfoKey implements Serializable {
    private Integer storeCode;
    private Integer tableNumber;
    private String menuCode;

    public MenuInfoKey() {}

    public MenuInfoKey(Integer storeCode, Integer tableNumber, String menuCode) {
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
        this.menuCode = menuCode;
    }

    public Integer getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(Integer storeCode) {
        this.storeCode = storeCode;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
