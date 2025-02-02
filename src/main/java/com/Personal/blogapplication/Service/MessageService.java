package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final RedisTemplate<String, String> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;
    private final NotificationService notificationService;


    private static final int MESSAGE_HISTORY_LIMIT = 100;

    public void SendMesage(MessageDTO messageDTO) {
        String message = messageDTO.getMessage();
        String groupName = messageDTO.getGroupName();
        redisTemplate.opsForZSet().add(groupName, message, System.currentTimeMillis());

        stringRedisTemplate.convertAndSend(groupName, message);
        notificationService.notifyGroupMembers(groupName, messageDTO.getUsername());
    }

    public List<String> getMessageHistory(String groupName) {
        Set<String> messages = redisTemplate.opsForZSet()
                .range(groupName, 0, MESSAGE_HISTORY_LIMIT - 1);

        return new ArrayList<>(messages);
    }

}
