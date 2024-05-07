package com.example.demo.DTO.ToClient;

public class S3Response {
    private String status;
    private String Url;

    public S3Response(String status, String url) {
        this.status = status;
        Url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
