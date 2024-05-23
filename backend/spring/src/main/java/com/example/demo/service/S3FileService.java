package com.example.demo.service;

import com.example.demo.DTO.ToServer.S3ModifyRequest;
import com.example.demo.DTO.ToServer.S3UploadRequest;
import com.example.demo.model.DataBase.MenuInfo;
import com.example.demo.repository.MenuInfoRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
public class S3FileService {

    private final AmazonS3 amazonS3Client;
    private final String bucket;
    private final String region;
    private final MenuInfoRepository menuInfoRepository;
    // 생성자 직접 정의

    public S3FileService(AmazonS3 amazonS3Client, String bucket, String region, MenuInfoRepository menuInfoRepository) {
        this.amazonS3Client = amazonS3Client;
        this.bucket = bucket;
        this.region = region;
        this.menuInfoRepository = menuInfoRepository;
    }

    public String uploadFile(MultipartFile file, int storeCode, String singleMenuCode) {
        try {
            String fileName = file.getOriginalFilename();
            String directory = "storeCode/" + storeCode + "/" + singleMenuCode;
            String fileUrl = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + directory + "/" + fileName;
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
            // null 일경우 case 추가 해야 함
            List<Integer> tableNumbers = menuInfoRepository.findTableNumbersByStoreCode(request.getStoreCode());
            // tableNumbers가 null이거나 비어 있으면 1로 설정
            if (tableNumbers == null || tableNumbers.isEmpty()) {
                tableNumbers = Collections.singletonList(1);
            }
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
            return "5003";
        }
    }
    @Transactional
    public String removeToDatabase(int storeCode, String menuName, String menuCode) {
        try {
            // 해당 storeCode와 menuCode를 가진 메뉴 정보를 조회
            List<MenuInfo> menus = menuInfoRepository.findByStoreCodeAndMenuAndMenuCode(storeCode, menuName, menuCode);

            if (menus.isEmpty()) {
                return "5004"; // 메뉴 정보가 없는 경우
            }

            // 조회된 메뉴 정보를 모두 삭제
            for (MenuInfo menu : menus) {
                menuInfoRepository.delete(menu);
            }

            return "200"; // 성공적으로 삭제 완료
        } catch (Exception e) {
            e.printStackTrace();
            return "5005"; // 예외 발생 시
        }
    }
    @Transactional
    public String modifyToDatabase(S3ModifyRequest request) {
        try {
            // 특정 가게 코드와 메뉴 코드를 기반으로 메뉴 정보를 조회합니다.
            List<MenuInfo> menuInfos = menuInfoRepository.findByStoreCodeAndMenuAndMenuCode(request.getStoreCode(), request.getMenu(),request.getMenuCode());

            // 조회된 메뉴 정보가 없을 경우
            if (menuInfos == null || menuInfos.isEmpty()) {
                return "5004";
            }

            // 모든 조회된 메뉴 정보에 대해 업데이트 수행
            for (MenuInfo menuInfo : menuInfos) {
                menuInfo.setMenu(request.getMenu());
                menuInfo.setPrice(request.getPrice());
                menuInfo.setIntroduce(request.getIntroduce());
                menuInfoRepository.save(menuInfo);
            }

            return "200";
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            e.printStackTrace();
            return "5006";
        }
    }

}
