package com.example.demo.DTO.ToServer;

public class S3ModifyRequest {
    private int storeCode;
    private String menuCode;
    private String menu;
    private String newMenu;
    private int price;
    private int recommend;
    private String introduce;
    private String singleMenuCode;

    private String jwtAdmin;

    public S3ModifyRequest(int storeCode, String menuCode, String menu, String newMenu, int price, int recommend, String introduce, String singleMenuCode, String jwtAdmin) {
        this.storeCode = storeCode;
        this.menuCode = menuCode;
        this.menu = menu;
        this.newMenu = newMenu;
        this.price = price;
        this.recommend = recommend;
        this.introduce = introduce;
        this.singleMenuCode = singleMenuCode;
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getNewMenu() {
        return newMenu;
    }

    public void setNewMenu(String newMenu) {
        this.newMenu = newMenu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }

    public String getSingleMenuCode() {
        return singleMenuCode;
    }

    public void setSingleMenuCode(String singleMenuCode) {
        this.singleMenuCode = singleMenuCode;
    }
}
