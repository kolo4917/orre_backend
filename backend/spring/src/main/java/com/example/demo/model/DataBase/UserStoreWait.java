package com.example.demo.model.DataBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "user_store_wait")
public class UserStoreWait {

    @Id
    @Column(name = "user_store_wait_phone_number")
    private Integer phoneNumber;

    @Id
    @Column(name = "user_store_wait_store_code")
    private Integer storeCode;

    @Column(name = "user_store_wait_waiting")
    private Integer waiting;

    @Column(name = "user_store_wait_status")
    private Integer status;

    public UserStoreWait() {}

    public UserStoreWait(Integer phoneNumber, Integer storeCode, Integer waiting, Integer status) {
        this.phoneNumber = phoneNumber;
        this.storeCode = storeCode;
        this.waiting = waiting;
        this.status = status;
    }

    public Integer getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(Integer phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public Integer getWaiting() { return waiting; }
    public void setWaiting(Integer waiting) { this.waiting = waiting; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
