package com.Personal.blogapplication.Interface;

import com.Personal.blogapplication.Dtos.UrlRequestDTO;
import com.Personal.blogapplication.Dtos.UrlResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface UrlService {
    @Transactional
//    UrlResponseDTO createShortUrl(UrlResponseDTO urlResponseDTO);

    UrlResponseDTO createShortUrl(UrlRequestDTO requestDTO);
    UrlResponseDTO getOriginalUrl(String shortCode);
    UrlResponseDTO updateShortUrl(String shortCode, UrlRequestDTO requestDTO);
    void deleteShortUrl(String shortCode);
    UrlResponseDTO getUrlStats(String shortCode);
}
