package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private String type;
    private String repoName;
    private String message;
    private String createdAt;
}