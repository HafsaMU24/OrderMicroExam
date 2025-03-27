package com.example.OrderMicroExam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/orders")
public class OrderService {

    private final WebClient webClient;

    private final OrderRepository orderRepository;

    public OrderService(WebClient webClient, OrderRepository orderRepository){
        this.webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> GETAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        boolean productExists = checkIfProductExists(order.getProduct());
        if (!productExists) {
            throw new RuntimeException("Product finns ej i ProductService");
        }
        //Default-status
        order.setStatus("PLACED");
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
    public Order updateOrder(Long id,Order updateOrder) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setProduct(updateOrder.getProduct());
                    existingOrder.setQuantity(updateOrder.getQuantity());
                    existingOrder.setStatus(updateOrder.getStatus());
                    existingOrder.setUserId(updateOrder.getUserId());
                    return orderRepository.save(existingOrder);
                } )
                .orElseThrow(( )-> new RuntimeException("Order med ID" + id + "hittades inte"));
    }
    private boolean checkIfProductExists(String productName) {
        try{
            String response = webClient.get()
                    .uri("/products/name/" + productName)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return response != null && !response.isEmpty();
        }
        catch (Exception e) {
            return false;
        }
    }
}
