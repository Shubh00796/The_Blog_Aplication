package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.UrlRequestDTO;
import com.Personal.blogapplication.Dtos.UrlResponseDTO;
import com.Personal.blogapplication.Entity.UrlEntity;
import com.Personal.blogapplication.Interface.UrlService;
import com.Personal.blogapplication.Repo.UrlRepository;
import com.Personal.blogapplication.Utils.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;


    @Override
    @Transactional
    public UrlResponseDTO createShortUrl(UrlRequestDTO requestDTO) {
        log.info("Creating short URL for: {}", requestDTO.getUrl());
        String shortCode = ShortCodeGenerator.generateShortCode();
        UrlEntity entity = UrlEntity.builder()
                .originalUrl(requestDTO.getUrl())
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .accessCount(0)
                .build();
        UrlEntity savedUrl = urlRepository.save(entity);
        return mapToResponseDTO(savedUrl);
    }

    private UrlResponseDTO mapToResponseDTO(UrlEntity entity) {
        return UrlResponseDTO.builder()
                .id(entity.getId())
                .accessCount(entity.getAccessCount())
                .url(entity.getOriginalUrl())
                .shortCode(entity.getShortCode())
                .createdAt(entity.getCreatedAt().toString())
                .updatedAt(entity.getUpdatedAt().toString())
                .build();
    }

    @Override
    @Transactional
    public UrlResponseDTO getOriginalUrl(String shortCode) {
        UrlEntity entity = urlRepository.findByShortCode(shortCode);
        if (entity == null) {
            log.error("Short URL not found");
            throw new IllegalArgumentException("Short URL not found");
        }
        entity.setAccessCount(entity.getAccessCount() + 1);
        urlRepository.save(entity);
        return mapToResponseDTO(entity);
    }

    @Override
    @Transactional
    public UrlResponseDTO updateShortUrl(String shortCode, UrlRequestDTO requestDTO) {
        UrlEntity entity = urlRepository.findByShortCode(shortCode);
        if (entity == null) {
            log.error("Short URL not found");
            throw new IllegalArgumentException("Short URL not found");
        }
        entity.setOriginalUrl(requestDTO.getUrl());
        entity.setUpdatedAt(LocalDateTime.now());
        urlRepository.save(entity);
        return mapToResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteShortUrl(String shortCode) {
        UrlEntity entity = urlRepository.findByShortCode(shortCode);
        if (entity == null) {
            log.error("Short URL not found");
            throw new IllegalArgumentException("Short URL not found");
        }
        urlRepository.delete(entity);
    }

    @Override
    public UrlResponseDTO getUrlStats(String shortCode) {
        UrlEntity entity = urlRepository.findByShortCode(shortCode);
        if (entity == null) {
            log.error("Short URL not found");
            throw new IllegalArgumentException("Short URL not found");
        }
        return mapToResponseDTO(entity);
    }
}
