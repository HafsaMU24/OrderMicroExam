package com.example.OrderMicroExam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/orders")
public class OrderService {

    private final ProductClient productClient;

    private final OrderRepository orderRepository;

    public OrderService(ProductClient productClient, OrderRepository orderRepository){
        this.productClient = productClient;
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
        boolean Exists = productClient.checkIfProductExists(order.getProduct());
        if (!Exists) {
            throw new RuntimeException("Produkten finns inte i  ProductService");
        }
        //Default-status
        order.setStatus("PLACED");
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
