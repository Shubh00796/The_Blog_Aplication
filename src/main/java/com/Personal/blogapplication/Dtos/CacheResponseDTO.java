package com.Personal.blogapplication.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheResponseDTO {

    private String data;
    private String cacheStatus;
}
