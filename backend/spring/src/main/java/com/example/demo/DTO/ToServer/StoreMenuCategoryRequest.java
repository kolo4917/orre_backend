package com.example.demo.DTO.ToServer;

public class StoreMenuCategoryRequest {
    private int storeCode;
    private String jwtAdmin;
    private String singleMenuCode;
    private String menuCategory;

    public StoreMenuCategoryRequest(int storeCode, String jwtAdmin, String singleMenuCode, String menuCategory) {
        this.storeCode = storeCode;
        this.jwtAdmin = jwtAdmin;
        this.singleMenuCode = singleMenuCode;
        this.menuCategory = menuCategory;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
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

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }
}
