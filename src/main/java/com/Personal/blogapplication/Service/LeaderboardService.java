package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.LeaderboardEntryDTO;
import com.Personal.blogapplication.Dtos.ScoreSubmissionDTO;
import com.Personal.blogapplication.Entity.LeaderboardEntry;
import com.Personal.blogapplication.Exceptions.LeaderboardException;
import com.Personal.blogapplication.Interface.LeaderboardRepository;
import com.Personal.blogapplication.Mappers.LeaderboardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;
    private final LeaderboardMapper mapper;

    public void submitScore(ScoreSubmissionDTO scoreSubmissionDTO) {
        LeaderboardEntry entry = LeaderboardEntry.builder()
                .userId(scoreSubmissionDTO.getUserId())
                .username(scoreSubmissionDTO.getUsername())
                .score(scoreSubmissionDTO.getScore())
                .timestamp(Instant.now().toEpochMilli())
                .build();

        leaderboardRepository.addOrUpdateScore(entry);
        leaderboardRepository.addScoreHistory(entry);

    }
    public List<LeaderboardEntryDTO> getGlobalLeaderboard(int limit) {
        List<LeaderboardEntry> topPlayers = leaderboardRepository.getTopPlayers(limit);
        List<LeaderboardEntryDTO> leaderboard = topPlayers.stream().map(entry -> {
            Long rank = leaderboardRepository.getUserRank(entry.getUserId()).orElse(null);
            LeaderboardEntryDTO dto = mapper.toDto(entry);
            dto.setRank(rank != null ? rank + 1 : null); // rank is 0-indexed, so add 1.
            return dto;
        }).collect(Collectors.toList());
        return leaderboard;
    }
    public LeaderboardEntryDTO getUserRanking(String userId) {
        LeaderboardEntryDTO dto = LeaderboardEntryDTO.builder().userId(userId).build();
        leaderboardRepository.getUserScore(userId).ifPresentOrElse(
                score -> dto.setScore(score),
                () -> { throw new LeaderboardException("User not found in leaderboard"); }
        );
        leaderboardRepository.getUserRank(userId).ifPresent(rank -> dto.setRank(rank + 1));
        return dto;
    }

    public List<LeaderboardEntryDTO> getUserHistory(String userId) {
        List<LeaderboardEntry> history = leaderboardRepository.getScoreHistory(userId);
        return history.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // Private helper method example for input validation.
    private void validateSubmission(ScoreSubmissionDTO submissionDTO) {
        if (submissionDTO.getUserId() == null || submissionDTO.getUserId().isEmpty()) {
            throw new LeaderboardException("User ID cannot be null or empty");
        }
        if (submissionDTO.getScore() < 0) {
            throw new LeaderboardException("Score cannot be negative");
        }
    }
}
