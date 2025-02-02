package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.LeaderboardEntryDTO;
import com.Personal.blogapplication.Dtos.ScoreSubmissionDTO;
import com.Personal.blogapplication.Service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @PostMapping("/score")
    public ResponseEntity<String> submitScore(@RequestBody ScoreSubmissionDTO submissionDTO) {
        leaderboardService.submitScore(submissionDTO);
        return ResponseEntity.ok("Score submitted successfully");
    }

    @GetMapping("/global")
    public ResponseEntity<List<LeaderboardEntryDTO>> getGlobalLeaderboard(
            @RequestParam(defaultValue = "10") int limit) {
        List<LeaderboardEntryDTO> leaderboard = leaderboardService.getGlobalLeaderboard(limit);
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping("/rank/{userId}")
    public ResponseEntity<LeaderboardEntryDTO> getUserRanking(@PathVariable String userId) {
        LeaderboardEntryDTO userRanking = leaderboardService.getUserRanking(userId);
        return ResponseEntity.ok(userRanking);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<LeaderboardEntryDTO>> getUserHistory(@PathVariable String userId) {
        List<LeaderboardEntryDTO> history = leaderboardService.getUserHistory(userId);
        return ResponseEntity.ok(history);
    }
}