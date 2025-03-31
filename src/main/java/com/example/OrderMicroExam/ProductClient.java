package com.example.OrderMicroExam;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ProductClient {

        private final WebClient webClient;

        public ProductClient(WebClient webClient) {
            this.webClient = webClient;
        }

    // Denna metod kallar productservice

    public boolean checkIfProductExists( String productName) {
        try{
            String response = webClient.get()
                    .uri("/products/name/{name}" , productName)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return response != null && !response.isEmpty();
        }
        catch (Exception e) {
            System.out.println("ERROR contacting ProductService: " + e.getMessage());
            return false;
        }
    }
}