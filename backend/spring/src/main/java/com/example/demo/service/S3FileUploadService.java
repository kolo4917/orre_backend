package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public class S3FileUploadService {

    private final AmazonS3 amazonS3Client;
    private final String bucket;
    // 생성자 직접 정의
    public S3FileUploadService(AmazonS3 amazonS3Client, String bucket) {
        this.amazonS3Client = amazonS3Client;
        this.bucket = bucket;
    }

    public String uploadFile(MultipartFile file, String storeCode, String name) throws IOException {
        String fileName = file.getOriginalFilename();
        String directory = "stores/" + storeCode + "/" + name; // 가게 코드와 이름을 사용하여 경로 생성
        String fileUrl = "https://" + bucket + "/" + directory + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, directory + "/" + fileName, file.getInputStream(), metadata);
        return fileUrl;
    }

}
