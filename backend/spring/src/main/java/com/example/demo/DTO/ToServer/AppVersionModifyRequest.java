package com.example.demo.DTO.ToServer;

public class AppVersionModifyRequest {
    private Integer appCode;
    private String newAppVersion;
    private Integer essential;

    public AppVersionModifyRequest(Integer appCode, String newAppVersion, Integer essential) {
        this.appCode = appCode;
        this.newAppVersion = newAppVersion;
        this.essential = essential;
    }

    public Integer getAppCode() {
        return appCode;
    }

    public void setAppCode(Integer appCode) {
        this.appCode = appCode;
    }

    public String getNewAppVersion() {
        return newAppVersion;
    }

    public void setNewAppVersion(String newAppVersion) {
        this.newAppVersion = newAppVersion;
    }

    public Integer getEssential() {
        return essential;
    }

    public void setEssential(Integer essential) {
        this.essential = essential;
    }
}
