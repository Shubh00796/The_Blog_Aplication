package com.Personal.blogapplication.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockAlertService implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String alertMessage = message.toString();
        log.info("Received stock alert: {}", alertMessage);

    }
}
