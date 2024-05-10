package com.example.demo.model.DataBase;
import java.io.Serializable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(UserLogId.class) // 복합 키 클래스 지정
@Table(name = "user_log")
public class UserLog {
    @Id
    @Column(name = "user_log_user_phone_number")
    private String userPhoneNumber;

    @Id
    @Column(name = "user_log_history_num")
    private int historyNum;

    @Column(name = "user_log_status")
    private String status;

    @Column(name = "user_log_make_waiting_time")
    private Date makeWaitingTime;

    @Column(name = "user_log_store_code")
    private int storeCode;

    @Column(name = "user_log_store_name")
    private String storeName;

    @Column(name = "user_log_entry_time")
    private Date entryTime;

    @Column(name = "user_log_paid_money")
    private int paidMoney;

    @Column(name = "user_log_ordered_menu", columnDefinition = "JSON")
    private String orderedMenu;

    public UserLog(String userPhoneNumber, int historyNum, String status, Date makeWaitingTime, int storeCode, String storeName, Date entryTime, int paidMoney, String orderedMenu) {
        this.userPhoneNumber = userPhoneNumber;
        this.historyNum = historyNum;
        this.status = status;
        this.makeWaitingTime = makeWaitingTime;
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.entryTime = entryTime;
        this.paidMoney = paidMoney;
        this.orderedMenu = orderedMenu;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(int historyNum) {
        this.historyNum = historyNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMakeWaitingTime() {
        return makeWaitingTime;
    }

    public void setMakeWaitingTime(Date makeWaitingTime) {
        this.makeWaitingTime = makeWaitingTime;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public int getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(int paidMoney) {
        this.paidMoney = paidMoney;
    }


    public String getOrderedMenu() {
        return orderedMenu;
    }

    public void setOrderedMenu(String orderedMenu) {
        this.orderedMenu = orderedMenu;
    }
}
    class UserLogId implements Serializable {
    private String userPhoneNumber;
    private int historyNum;

    // 기본 생성자
    public UserLogId() {}

    // 매개변수 있는 생성자
    public UserLogId(String userPhoneNumber, int historyNum) {
        this.userPhoneNumber = userPhoneNumber;
        this.historyNum = historyNum;
    }

}
