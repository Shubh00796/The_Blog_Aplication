package com.Personal.blogapplication.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {
    private final RedisTemplate<String, String> redisTemplate;

    public void addUserToGroup(String groupName, String username) {
        log.info("Attempting to add user {} to group {}", username, groupName);
        redisTemplate.opsForSet().add(groupName, username);  // Let exception propagate for now
        log.info("User {} added to group {}", username, groupName);
    }

    public void removeUserFromGroup(String groupName, String username) {
        try {
            log.info("Attempting to remove user {} from group {}", username, groupName);
            redisTemplate.opsForSet().remove(groupName, username);
            log.info("User {} removed from group {}", username, groupName);
        } catch (Exception e) {
            log.error("Failed to remove user {} from group {}. Error: {}", username, groupName, e.getMessage());
            throw new RedisSystemException("Error removing user from group", e);
        }
    }

    public Set<String> getGroupMembers(String groupName) {
        try {
            log.info("Retrieving members for group {}", groupName);
            return redisTemplate.opsForSet().members(groupName);
        } catch (Exception e) {
            log.error("Failed to retrieve members for group {}. Error: {}", groupName, e.getMessage());
            throw new RedisSystemException("Error retrieving group members", e);
        }
    }
}
