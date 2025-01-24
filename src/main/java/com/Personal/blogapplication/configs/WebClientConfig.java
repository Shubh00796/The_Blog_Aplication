package com.Personal.blogapplication.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
public class WebClientConfig {

    private final TcpClient tcpClient;

    public WebClientConfig(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.from(tcpClient);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
