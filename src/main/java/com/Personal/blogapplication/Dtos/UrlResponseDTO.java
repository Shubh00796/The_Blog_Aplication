package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlResponseDTO {
    private Long id;
    private String url;
    private String shortCode;
    private String createdAt;
    private String updatedAt;
    private int accessCount;
}
