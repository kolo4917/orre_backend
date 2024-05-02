package com.example.demo.DTO.ToServer;

public class StoreMenuAvailableRequest {
    private int storeCode;
    private String jwtAdmin;
    private int availableCode; // 가능 = 1, 불가능 = 0
    private String menuCode; // 해당 메뉴의 코드, 전체 제어시 * : 주방 마감시 전체 메뉴 주문 불가

    public StoreMenuAvailableRequest(int storeCode, String jwtAdmin, int availableCode, String menuCode) {
        this.storeCode = storeCode;
        this.jwtAdmin = jwtAdmin;
        this.availableCode = availableCode;
        this.menuCode = menuCode;
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

    public int getAvailableCode() {
        return availableCode;
    }

    public void setAvailableCode(int availableCode) {
        this.availableCode = availableCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
