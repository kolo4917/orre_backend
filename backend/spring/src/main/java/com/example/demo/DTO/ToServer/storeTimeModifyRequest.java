package com.example.demo.DTO.ToServer;

import java.sql.Time;

public class storeTimeModifyRequest {
    private Time newOpeningTime;
    private Time newClosingTime;
    private Time newLastOrderTime;
    private Time newStartBreakTime;
    private Time newEndBreakTime;

    public storeTimeModifyRequest(Time newOpeningTime, Time newClosingTime, Time newLastOrderTime, Time newStartBreakTime, Time newEndBreakTime) {
        this.newOpeningTime = newOpeningTime;
        this.newClosingTime = newClosingTime;
        this.newLastOrderTime = newLastOrderTime;
        this.newStartBreakTime = newStartBreakTime;
        this.newEndBreakTime = newEndBreakTime;

    }

    public Time getNewOpeningTime() {
        return newOpeningTime;
    }

    public void setNewOpeningTime(Time newOpeningTime) {
        this.newOpeningTime = newOpeningTime;
    }

    public Time getNewClosingTime() {
        return newClosingTime;
    }

    public void setNewClosingTime(Time newClosingTime) {
        this.newClosingTime = newClosingTime;
    }

    public Time getNewLastOrderTime() {
        return newLastOrderTime;
    }

    public void setNewLastOrderTime(Time newLastOrderTime) {
        this.newLastOrderTime = newLastOrderTime;
    }

    public Time getNewStartBreakTime() {
        return newStartBreakTime;
    }

    public void setNewStartBreakTime(Time newStartBreakTime) {
        this.newStartBreakTime = newStartBreakTime;
    }

    public Time getNewEndBreakTime() {
        return newEndBreakTime;
    }

    public void setNewEndBreakTime(Time newEndBreakTime) {
        this.newEndBreakTime = newEndBreakTime;
    }
}
