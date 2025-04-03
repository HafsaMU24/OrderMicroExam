package com.example.OrderMicroExam;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class OrderClient {

        private final WebClient webClient;

        public OrderClient(WebClient.Builder  webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
        }

    // Denna metod kallar productservice
    public Mono<String> getOrder(String orderName) {
            return webClient.get()
                    .uri("/products/name/{name}" , orderName)
                    .retrieve()
                    .bodyToMono(String.class);
    }
}