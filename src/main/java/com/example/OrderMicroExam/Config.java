package com.example.OrderMicroExam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class Config {

private WebClient webClient;
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}