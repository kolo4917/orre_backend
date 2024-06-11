package com.example.demo.controller;

import com.example.demo.DTO.ToClient.StatusResponse;
import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.DTO.ToServer.S3RemoveRequest;
import com.example.demo.DTO.ToServer.S3ModifyRequest;
import com.example.demo.service.S3FileService;
import com.example.demo.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3FileController {

    private final S3FileService s3FileService;
    private final JwtService jwtService;

    public S3FileController(S3FileService s3FileService, JwtService jwtService) {
        this.s3FileService = s3FileService;
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
            String fileUrl = s3FileService.uploadFile(file, storeCode, singleMenuCode);
            if(!fileUrl.equals("5001")){
                String uploadToDatabase = s3FileService.uploadToDatabase(request, fileUrl);
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
            String status = s3FileService.removeToDatabase(storeCode, menu, menuCode);
            return new StatusResponse(status);
            }
        else {
            return new StatusResponse("5001");
        }
    }
    @PostMapping("/api/admin/StoreAdmin/menu/s3/modify")
    public StatusResponse modifyFile(
            @RequestPart(name = "file", required = false) MultipartFile file,
            @RequestPart(name = "request") S3ModifyRequest request
    ) {
        int storeCode = request.getStoreCode();
        String singleMenuCode = request.getSingleMenuCode();
        String jwtAdmin = request.getJwtAdmin();

        boolean isValidAdmin = jwtService.isValid(jwtAdmin);
        if (isValidAdmin) {
            String fileUrl = null;
            if (file != null && !file.isEmpty()) {
                fileUrl = s3FileService.uploadFile(file, storeCode, singleMenuCode);
            }
            String status = s3FileService.modifyToDatabase(request, fileUrl);
            return new StatusResponse(status);
        } else {
            return new StatusResponse("5001");
        }
    }



}
