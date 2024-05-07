package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.service.S3FileUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@RestController
public class S3FileUploadController {

    private final S3FileUploadService s3FileUploadService;

    public S3FileUploadController(S3FileUploadService s3FileUploadService) {
        this.s3FileUploadService = s3FileUploadService;
    }

    @PostMapping("/api/admin/StoreAdmin/menu/upload")
    public StatusResponse uploadFile(@RequestPart(name = "file") MultipartFile file,
                                 @RequestPart (name = "request") S3UploadRequest request) {
        int storeCode = request.getStoreCode();
        String singleMenuCode = request.getSingleMenuCode();
        String fileUrl = s3FileUploadService.uploadFile(file, storeCode, singleMenuCode);

        if(!fileUrl.equals("1201")){
            String uploadToDatabase = s3FileUploadService.uploadToDatabase(request, fileUrl);
            return new StatusResponse(uploadToDatabase);
        }
        return new StatusResponse("1201");
    }

}
