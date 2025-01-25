package com.Personal.blogapplication.Controller;


import com.Personal.blogapplication.Dtos.UrlRequestDTO;
import com.Personal.blogapplication.Dtos.UrlResponseDTO;
import com.Personal.blogapplication.Interface.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlResponseDTO> createShortUrl(@RequestBody UrlRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(urlService.createShortUrl(requestDTO));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getOriginalUrl(shortCode));
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDTO> updateShortUrl(@PathVariable String shortCode, @RequestBody UrlRequestDTO requestDTO) {
        return ResponseEntity.ok(urlService.updateShortUrl(shortCode, requestDTO));
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteShortUrl(@PathVariable String shortCode) {
        urlService.deleteShortUrl(shortCode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<UrlResponseDTO> getUrlStats(@PathVariable String shortCode) {
        return ResponseEntity.ok(urlService.getUrlStats(shortCode));
    }
}
