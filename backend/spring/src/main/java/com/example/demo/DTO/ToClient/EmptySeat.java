package com.example.demo.DTO.ToClient;

public class EmptySeat {
    private Integer tableNumber;
    private Integer tablePersonNumber;
    private Integer tableAvailable;

    public EmptySeat(Integer tableNumber, Integer tablePersonNumber, Integer tableAvailable) {
        this.tableNumber = tableNumber;
        this.tablePersonNumber = tablePersonNumber;
        this.tableAvailable = tableAvailable;
    }

    // Getter and Setter
    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public Integer getTablePersonNumber() { return tablePersonNumber; }
    public void setTablePersonNumber(Integer tablePersonNumber) { this.tablePersonNumber = tablePersonNumber; }

    public Integer getTableAvailable() { return tableAvailable; }
    public void setTableAvailable(Integer tableAvailable) { this.tableAvailable = tableAvailable; }
}
