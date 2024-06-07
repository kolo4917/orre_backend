package com.example.demo.service;

import java.util.HashMap;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.KakaoOption;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoWaitingMakeService {
    private final DefaultMessageService messageService;

    private final String apiKey;
    private final String apiSecret;
    private final String senderNumber;
    private final String pfId;
    private final String templeteId;

    @Autowired // 생성자에서 필요한 값들을 주입받습니다.
    public KakaoWaitingMakeService(
            @Value("${coolsms.apikey}") String apiKey,
            @Value("${coolsms.apisecret}") String apiSecret,
            @Value("${coolsms.fromnumber}") String senderNumber,
            @Value("${coolsms.pfid}") String pfId,
            @Value("${coolsms.templeteid2}")String templeteId) {

        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.senderNumber = senderNumber;
        this.pfId = pfId;
        this.templeteId = templeteId;
        this.messageService = NurigoApp.INSTANCE.initialize(this.apiKey, this.apiSecret, "https://api.coolsms.co.kr");
    }

    public SingleMessageSentResponse sendOneAta(String userPhoneNumber, Integer waitingTeam, String storeName, Integer waitingTime) {
        KakaoOption kakaoOption = new KakaoOption();

        // disableSms = true로 설정하실 경우 문자로 대체발송 되지 않습니다.
        kakaoOption.setDisableSms(true);

        kakaoOption.setPfId(pfId);
        kakaoOption.setTemplateId(templeteId);

        HashMap<String, String> variables = new HashMap<>();
        variables.put("#{waitingTeam}", Integer.toString(waitingTeam));
        variables.put("#{storeName}", storeName);
        variables.put("#{waitingTime}", Integer.toString(waitingTime));
        kakaoOption.setVariables(variables);

        Message message = new Message();
        message.setFrom(senderNumber);
        message.setTo(userPhoneNumber);
        message.setKakaoOptions(kakaoOption);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }

}
