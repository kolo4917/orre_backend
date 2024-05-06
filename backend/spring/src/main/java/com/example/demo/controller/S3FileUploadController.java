/*package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.service.S3FileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class S3FileUploadController {

    private final S3FileUploadService s3FileUploadService;

    public S3FileUploadController(S3FileUploadService s3FileUploadService) {
        this.s3FileUploadService = s3FileUploadService;
    }

    /*@PostMapping("/api/upload")
    public StatusResponse uploadFile(@RequestParam("file") MultipartFile file,
                                     @RequestBody S3UploadRequest request) {
        try {
            int storeCode = request.getStoreCode();
            //String name = request.ge();
            //String fileUrl = s3FileUploadService.uploadFile(file, storeCode, name);
            return new StatusResponse("200: File uploaded successfully. URL: " + fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return new StatusResponse("1201: Failed to upload the file. Error: " + e.getMessage());
        }
    }
}
*/