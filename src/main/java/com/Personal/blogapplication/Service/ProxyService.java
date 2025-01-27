package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.CacheRequestDTO;
import com.Personal.blogapplication.Dtos.CacheResponseDTO;
import com.Personal.blogapplication.Entity.CacheEntry;
import com.Personal.blogapplication.Repo.CacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProxyService {

    private final WebClient webClient;
    private final CacheRepository cacheRepository;

    public CacheResponseDTO handleRequest(CacheRequestDTO requestDTO, String originUrl) {
        String fullPath = originUrl + requestDTO.getPath();

        // Check cache
        return cacheRepository.findByRequestPath(requestDTO.getPath())
                .map(cacheEntry -> {
                    log.info("Cache HIT for path: {}", requestDTO.getPath());
                    return new CacheResponseDTO(cacheEntry.getResponseData(), "HIT");
                }).orElseGet(() -> fetchAndCacheResponse(requestDTO.getPath(), fullPath));
    }

    private CacheResponseDTO fetchAndCacheResponse(String path, String fullPath) {
        log.info("Cache MISS for path: {}. Forwarding to origin.", path);

        String responseData = webClient.get()
                .uri(fullPath)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        CacheEntry cached = new CacheEntry(null, path, responseData, "", System.currentTimeMillis());
        cacheRepository.save(cached);

        return new CacheResponseDTO(responseData, "MISS");
    }

    public void clearCache() {
        log.info("Clearing cache...");
        cacheRepository.deleteAll();
    }
}
