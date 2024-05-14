package com.example.demo.DTO.ToClient;
import java.util.List;
import com.example.demo.model.DataBase.UserLog;

public class LogResponse {
    private List<UserLog> userLogs;
    private String status;

    public LogResponse(List<UserLog> userLogs, String status) {
        this.userLogs = userLogs;
        this.status = status;
    }

    public List<UserLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<UserLog> userLogs) {
        this.userLogs = userLogs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
