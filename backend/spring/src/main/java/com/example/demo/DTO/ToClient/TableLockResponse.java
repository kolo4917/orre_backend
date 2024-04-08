package com.example.demo.DTO.ToClient;

public class TableLockResponse {
    private boolean success;
    private int storeCode;
    private int tableNumber;

    public TableLockResponse(boolean success, int storeCode, int tableNumber) {
        this.success = success;
        this.storeCode = storeCode;
        this.tableNumber = tableNumber;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
