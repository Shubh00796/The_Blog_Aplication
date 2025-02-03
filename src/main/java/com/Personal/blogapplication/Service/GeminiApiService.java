package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.GeminiRequest;
import com.Personal.blogapplication.Dtos.GeminiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service

@Slf4j
public class GeminiApiService {
    private final WebClient webClient;
    private final String geminiApiUrl;

    public GeminiApiService(WebClient.Builder webClientBuilder, @Value("${gemini.api.url}") String geminiApiUrl) {
        this.webClient = webClientBuilder.baseUrl(geminiApiUrl).build();
        this.geminiApiUrl = geminiApiUrl;
    }

    @Value("${gemini.api.key}")
    private String apiKey;

    public Mono<GeminiResponse> generateContent(GeminiRequest geminiRequest) {
        log.info("Sending request to Gemini API with payload: {}", geminiRequest);

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .header("Content-Type", "application/json")
                .bodyValue(geminiRequest)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .onErrorMap(WebClientResponseException.class, ex ->
                        new RuntimeException("API request failed: " + ex.getMessage()))
                .doOnError(error -> log.error("Error occurred while making API request: {}", error.getMessage()));
    }


}

