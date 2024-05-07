package com.example.demo.service;

import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.model.DataBase.MenuInfo;
import com.example.demo.repository.MenuInfoRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
public class S3FileUploadService {

    private final AmazonS3 amazonS3Client;
    private final String bucket;
    private final String region;
    private final MenuInfoRepository menuInfoRepository;
    // 생성자 직접 정의

    public S3FileUploadService(AmazonS3 amazonS3Client, String bucket, String region, MenuInfoRepository menuInfoRepository) {
        this.amazonS3Client = amazonS3Client;
        this.bucket = bucket;
        this.region = region;
        this.menuInfoRepository = menuInfoRepository;
    }

    public String uploadFile(MultipartFile file, int storeCode, String singleMenuCode) {
        try {
            String fileName = file.getOriginalFilename();
            String directory = "storeCode/" + storeCode + "/" + singleMenuCode;
            String fileUrl = "https://" + bucket + "." + region + ".amazonaws.com/" + directory + "/" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, directory + "/" + fileName, file.getInputStream(), metadata);
            return fileUrl;
        } catch (IOException e) {
            // IOException 발생 시 예외 처리
            e.printStackTrace();
            return "5001"; // 또는 예외를 상위로 전파하거나 다른 방식으로 처리할 수 있음
        }
    }
    @Transactional
    public String uploadToDatabase(S3UploadRequest request, String url) {
        try {
            // 특정 가게 코드에 대한 테이블 번호를 조회
            List<Integer> tableNumbers = menuInfoRepository.findTableNumbersByStoreCode(request.getStoreCode());

            // 조회된 테이블 번호 리스트의 길이 만큼 1부터 처리하여 메뉴를 추가
            for (int i = 1; i <= tableNumbers.size(); i++) {
                MenuInfo menuInfo = new MenuInfo(
                        request.getStoreCode(),
                        i, // 테이블 번호
                        request.getMenu(),
                        request.getPrice(),
                        0, // amount는 어디서 받는지에 따라 결정
                        request.getMenuCode(),
                        1, // available 기본값 1로 설정 (활성화 상태)
                        0, // recommend 기본값 0으로 설정
                        url, // 이미지 URL
                        request.getIntroduce()
                );
                menuInfoRepository.save(menuInfo);
            }

            return "200";
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            return "5002";
        }
    }



}
