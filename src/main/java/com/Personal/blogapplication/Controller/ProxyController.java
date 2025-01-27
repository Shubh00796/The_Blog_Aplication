package com.Personal.blogapplication.Controller;

import com.Personal.blogapplication.Dtos.CacheRequestDTO;
import com.Personal.blogapplication.Dtos.CacheResponseDTO;
import com.Personal.blogapplication.Service.ProxyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private final ProxyService proxyService;

    @Value("${origin.url}")
    private String originUrl; // URL to forward requests to (for example, "https://jsonplaceholder.typicode.com")

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping("/handle")
    public CacheResponseDTO handleRequest(@RequestBody CacheRequestDTO requestDTO) {
        return proxyService.handleRequest(requestDTO, originUrl);
    }

    @DeleteMapping("/clear-cache")
    public void clearCache() {
        proxyService.clearCache();
    }
}
