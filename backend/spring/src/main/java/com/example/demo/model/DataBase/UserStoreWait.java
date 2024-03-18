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
    private String phoneNumber;

    @Id
    @Column(name = "user_store_wait_store_code")
    private Integer storeCode;

    @Column(name = "user_store_wait_waiting") //웨이팅 번호
    private Integer waiting;

    @Column(name = "user_store_wait_status")
    private Integer status;



    @Column(name = "user_store_wait_person_number")
    private Integer personNumber;

    public UserStoreWait() {}

    public UserStoreWait(String phoneNumber, Integer storeCode, Integer waiting, Integer status, Integer personNumber) {
        this.phoneNumber = phoneNumber;
        this.storeCode = storeCode;
        this.waiting = waiting;
        this.status = status;
        this.personNumber = personNumber;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Integer getStoreCode() { return storeCode; }
    public void setStoreCode(Integer storeCode) { this.storeCode = storeCode; }

    public Integer getWaiting() { return waiting; }
    public void setWaiting(Integer waiting) { this.waiting = waiting; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getPersonNumber() {return personNumber;}

    public void setPersonNumber(Integer personNumber) {this.personNumber = personNumber;}
}
