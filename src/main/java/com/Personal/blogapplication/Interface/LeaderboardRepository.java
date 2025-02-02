package com.Personal.blogapplication.Interface;

import com.Personal.blogapplication.Entity.LeaderboardEntry;

import java.util.List;
import java.util.Optional;

public interface LeaderboardRepository {
    void addOrUpdateScore(LeaderboardEntry entry);
    List<LeaderboardEntry> getTopPlayers(int limit);
    Optional<Long> getUserRank(String userId);
    Optional<Double> getUserScore(String userId);
    void addScoreHistory(LeaderboardEntry entry);
    List<LeaderboardEntry> getScoreHistory(String userId);
}
