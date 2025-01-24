package com.Personal.blogapplication.Service;

import com.Personal.blogapplication.Dtos.ActivityDTO;
import com.Personal.blogapplication.Exceptions.CustomException;
import com.Personal.blogapplication.Utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GitHubActivityService {

    @Autowired
    private WebClient webClient;

    public List<ActivityDTO> getUserActivity(String username) {
        try {
            log.info("Fetching activity for user: {}", username);
            return webClient.get()
                    .uri(Constants.GITHUB_EVENTS_API + username + "/events")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                        log.error("Client error while fetching data: {}", clientResponse.statusCode());
                        return Mono.error(new RuntimeException("Client Error: " + clientResponse.statusCode()));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, serverResponse -> {
                        log.error("Server error while fetching data: {}", serverResponse.statusCode());
                        return Mono.error(new RuntimeException("Server Error: " + serverResponse.statusCode()));
                    })
                    .bodyToFlux(ActivityDTO.class)
                    .collectList()
                    .block()
                    .stream()
                    .map(event -> new ActivityDTO(
                            event.getType(),
                            event.getRepoName(),
                            event.getMessage(),
                            event.getCreatedAt()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching activity for user: {}", username, e);
            throw new CustomException("Failed to fetch activity for user: " + username);
        }
    }
}
