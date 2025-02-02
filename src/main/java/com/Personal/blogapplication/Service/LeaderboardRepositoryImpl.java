package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Entity.LeaderboardEntry;
import com.Personal.blogapplication.Interface.LeaderboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class LeaderboardRepositoryImpl implements LeaderboardRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final String LEADERBOARD_KEY = "leaderboard";

    @Override
    public void addOrUpdateScore(LeaderboardEntry entry) {

        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        zSetOps.incrementScore(LEADERBOARD_KEY, entry.getUserId(), entry.getScore());

    }

    @Override
    public List<LeaderboardEntry> getTopPlayers(int limit) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        Set<Object> range = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit - 1);
        List<LeaderboardEntry> entries = new ArrayList<>();
        if (range != null) {
            for (Object userIdObj : range) {
                String userId = (String) userIdObj;
                Double score = zSetOps.score(LEADERBOARD_KEY, userId);
                entries.add(LeaderboardEntry.builder()
                        .userId(userId)
                        .score(score != null ? score : 0)
                        .timestamp(Instant.now().toEpochMilli())
                        .build()

                );
            }

        }

        return entries;
    }

    @Override
    public Optional<Long> getUserRank(String userId) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY, userId);
        return Optional.ofNullable(rank);
    }

    @Override
    public Optional<Double> getUserScore(String userId) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(LEADERBOARD_KEY, userId);
        return Optional.ofNullable(score);
    }

    @Override
    public void addScoreHistory(LeaderboardEntry entry) {
        String historyKey = "leaderboard:history:" + entry.getUserId();
        if (entry.getTimestamp() == 0) {
            entry.setTimestamp(Instant.now().toEpochMilli());
        }
        redisTemplate.opsForList().leftPush(historyKey, entry);

    }

    @Override
    public List<LeaderboardEntry> getScoreHistory(String userId) {
        String historyKey = "leaderboard:history:" + userId;
        List<Object> list = redisTemplate.opsForList().range(historyKey, 0, -1);
        List<LeaderboardEntry> history = new ArrayList<>();
        if (list != null) {
            for (Object obj : list) {


                history.add((LeaderboardEntry) obj);
            }
        }
        return history;
    }
}
