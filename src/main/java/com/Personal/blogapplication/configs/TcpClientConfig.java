package com.Personal.blogapplication.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.tcp.TcpClient;

@Configuration
public class TcpClientConfig {

    @Bean
    public TcpClient tcpClient() {
        return TcpClient.create(); // Customize the TcpClient if needed
    }
}