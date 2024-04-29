package com.example.demo.service;
import com.example.demo.repository.UserSaveRepository;
import com.example.demo.model.DataBase.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SignupService {

    private final DefaultMessageService messageService;
    private final Map<String, String> verificationCodeMap;
    @Autowired
    private UserSaveRepository userSaveRepository; // UserRepository 주입
    private final String apiKey;
    private final String apiSecret;
    private final String senderNumber;

    @Autowired // 생성자에서 필요한 값들을 주입받습니다.
    public SignupService(
            @Value("${coolsms.apikey}") String apiKey,
            @Value("${coolsms.apisecret}") String apiSecret,
            @Value("${coolsms.fromnumber}") String senderNumber) {

        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.senderNumber = senderNumber;
        this.messageService = NurigoApp.INSTANCE.initialize(this.apiKey, this.apiSecret, "https://api.coolsms.co.kr");
        this.verificationCodeMap = new HashMap<>();
    }

    // 전화번호를 입력받아 인증번호를 생성하고 전송하는 메서드
    public String sendVerificationSMS(String phoneNumber) {
        // 인증번호 생성
        String verificationCode = generateVerificationCode();

// 전화번호에서 중괄호 제거
        //phoneNumber = phoneNumber.replaceAll("[{}]", "");

        // 메시지 객체 생성
        Message message = new Message();
        message.setFrom(senderNumber); // 발신전화번호
        message.setTo(phoneNumber); // 수신전화번호
        message.setText("[ORRE] 문자 본인인증 서비스 : 인증번호는 [" + verificationCode + "] 입니다.");

        try {
            SingleMessageSentResponse response;
            response = messageService.sendOne(new SingleMessageSendingRequest(message));
            System.out.println(response);
            // 전화번호와 인증번호 맵에 저장
            verificationCodeMap.put(phoneNumber, verificationCode);
            System.out.println(verificationCode);
            return verificationCode;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // 인증번호 생성 메서드
    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) { //6 자리의 랜덤한 수 보냄
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // 인증번호 확인 및 사용자 정보 DB 저장
    public boolean registerUser(String phoneNumber, String verificationCode, String password, String userName) {
        // 맵에서 유저의 인증번호 가져오기
        String storedVerificationCode = verificationCodeMap.get(phoneNumber);
        System.out.println(storedVerificationCode+"////////"+verificationCode);
        // 맵에 저장된 인증번호와 유저가 입력한 인증번호 비교
        if (storedVerificationCode != null && storedVerificationCode.equals(verificationCode)) {
            User newUser = new User();
            newUser.setPhoneNumber(phoneNumber);
            newUser.setPassword(password); // 실제 서비스에서는 비밀번호를 해시하여 저장
            newUser.setName(userName);
            newUser.setStoreCode(1);
            newUser.setToken("token9999");
            userSaveRepository.save(newUser);

            // 인증번호 맵에서 해당 전화번호 삭제
            verificationCodeMap.remove(phoneNumber);

            System.out.println("회원가입 완료: " + phoneNumber);
            return true;
        } else {
            System.out.println("회원가입 실패: 인증번호 불일치");
            return false;
        }
    }
}
