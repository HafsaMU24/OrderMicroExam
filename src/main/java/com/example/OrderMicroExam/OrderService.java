package com.example.OrderMicroExam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/orders")
public class OrderService {

    private final OrderClient orderClient;

    private final OrderRepository orderRepository;

    public OrderService(OrderClient orderClient, OrderRepository orderRepository){
        this.orderClient = orderClient;
        this.orderRepository = orderRepository;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

   @GetMapping
    public List<Order> GETAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updateOrder) {
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
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

}
