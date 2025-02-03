package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Utils.RedisConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisherService {


    private final RedisTemplate<String, Object> redisTemplate;



    public void publishStockAlert(String symbol, String message) {
        redisTemplate.convertAndSend(RedisConstants.ALERTS_CHANNEL + symbol, message);
    }

}


