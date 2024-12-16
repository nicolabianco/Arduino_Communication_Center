package com.arduino.service.impl;

import com.arduino.service.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
public class TelegramServiceImpl implements TelegramService {


    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot{token}/sendMessage";

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.chatId}")
    private String chatId;

    @Override
    public void sendMessage(String temperatura) {
        RestTemplate restTemplate = new RestTemplate();
        String url = TELEGRAM_API_URL.replace("{token}", botToken);
        var requestParams = new LinkedMultiValueMap<String, String>();
        requestParams.add("chat_id", chatId);
        requestParams.add("text", "temperatura rilevata di " + temperatura + " gradi");
        restTemplate.postForObject(url, requestParams, String.class);
    }

}
