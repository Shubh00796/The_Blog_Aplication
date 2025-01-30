package com.Personal.blogapplication.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private Long id;
    private String username;
    private String token;
}
