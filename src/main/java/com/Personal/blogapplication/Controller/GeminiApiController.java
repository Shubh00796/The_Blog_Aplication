package com.Personal.blogapplication.Controller;


import com.Personal.blogapplication.Dtos.GeminiRequest;
import com.Personal.blogapplication.Dtos.GeminiResponse;
import com.Personal.blogapplication.Service.GeminiApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
@Slf4j
public class GeminiApiController {

    private final GeminiApiService geminiApiService;

    @PostMapping("/generate")
    public Mono<ResponseEntity<GeminiResponse>> generateContent(@RequestBody GeminiRequest geminiRequest) {
        log.info("Received request for Gemini API: {}", geminiRequest);
        return geminiApiService.generateContent(geminiRequest)
                .map(response -> ResponseEntity.ok().body(response))
                .doOnSuccess(response -> log.info("Successfully processed request"))
                .doOnError(error -> log.error("Error processing request: {}", error.getMessage()));
    }
}
