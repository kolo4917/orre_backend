package com.example.demo.DTO.ToServer;

public class S3RemoveRequest {
    private int storeCode;
    private String menuCode;
    private String menu;
    private String jwtAdmin;

    public S3RemoveRequest(int storeCode, String menuCode, String menu, String jwtAdmin) {
        this.storeCode = storeCode;
        this.menuCode = menuCode;
        this.menu = menu;
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

    public String getJwtAdmin() {
        return jwtAdmin;
    }

    public void setJwtAdmin(String jwtAdmin) {
        this.jwtAdmin = jwtAdmin;
    }
}
