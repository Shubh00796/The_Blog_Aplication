package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardEntryDTO {
    private String userId;
    private String username;
    private double score;
    private long timestamp;
    private Long rank;
}