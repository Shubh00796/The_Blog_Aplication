package com.Personal.blogapplication.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final StringRedisTemplate stringRedisTemplate;

    public void notifyGroupMembers(String groupName, String sender) {
        String notification = sender + " sent a message in " + groupName;
        stringRedisTemplate.convertAndSend(groupName + ":notifications", notification);
    }

}
