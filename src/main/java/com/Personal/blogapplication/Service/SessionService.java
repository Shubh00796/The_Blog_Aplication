package com.Personal.blogapplication.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final RedisTemplate<String, String> redisTemplate;

    public void storeSession(String username, String sessionId) {
        redisTemplate.opsForHash().put("active_sessions", username, sessionId);
    }

    public String getSession(String username) {
        return (String) redisTemplate.opsForHash().get("active_sessions", username);
    }

    public void removeSession(String username) {
        redisTemplate.opsForHash().delete("active_sessions", username);
    }

}
