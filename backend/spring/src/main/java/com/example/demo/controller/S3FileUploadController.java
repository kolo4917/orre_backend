package com.example.demo.controller;

import com.example.demo.DTO.ToClient.BooleanResponse;
import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.DTO.ToServer.S3RemoveRequest;
import com.example.demo.service.S3FileUploadService;
import com.example.demo.service.JwtService;
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
    private final JwtService jwtService;

    public S3FileUploadController(S3FileUploadService s3FileUploadService, JwtService jwtService) {
        this.s3FileUploadService = s3FileUploadService;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/admin/StoreAdmin/menu/s3/upload")
    public StatusResponse uploadFile(@RequestPart(name = "file") MultipartFile file,
                                 @RequestPart (name = "request") S3UploadRequest request) {
        int storeCode = request.getStoreCode();
        String singleMenuCode = request.getSingleMenuCode();
        String jwtAdmin = request.getJwtAdmin();

        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String fileUrl = s3FileUploadService.uploadFile(file, storeCode, singleMenuCode);
            if(!fileUrl.equals("5001")){
                String uploadToDatabase = s3FileUploadService.uploadToDatabase(request, fileUrl);
                return new StatusResponse(uploadToDatabase);
            }
            return new StatusResponse("5002");
        }
        else {
            return new StatusResponse("5001");
        }

    }
    @PostMapping("/api/admin/StoreAdmin/menu/s3/remove")
    public StatusResponse removeFile(@RequestBody S3RemoveRequest request){

        String jwtAdmin = request.getJwtAdmin();
        int storeCode = request.getStoreCode();
        String menu = request.getMenu();
        String menuCode = request.getMenuCode();

        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String status = s3FileUploadService.removeToDatabase(storeCode, menu, menuCode);
            return new StatusResponse(status);
            }
        else {
            return new StatusResponse("5001");
        }

    }


}
