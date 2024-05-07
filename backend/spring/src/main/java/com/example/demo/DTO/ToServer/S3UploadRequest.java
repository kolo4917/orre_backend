package com.example.demo.DTO.ToServer;

public class S3UploadRequest {
    private int storeCode;
    private String menu;
    private int price;
    private String singleMenuCode;
    private String menuCode;
    private String introduce;
    private String jwtAdmin;

    public S3UploadRequest(int storeCode, String menu, int price, String singleMenuCode, String menuCode, String introduce, String jwtAdmin) {
        this.storeCode = storeCode;
        this.menu = menu;
        this.price = price;
        this.singleMenuCode = singleMenuCode;
        this.menuCode = menuCode;
        this.introduce = introduce;
        this.jwtAdmin = jwtAdmin;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSingleMenuCode() {
        return singleMenuCode;
    }

    public void setSingleMenuCode(String singleMenuCode) {
        this.singleMenuCode = singleMenuCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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
}
