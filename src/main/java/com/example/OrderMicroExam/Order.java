package com.example.OrderMicroExam;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private Long user_id;
    private String product;
    private String status;
    private int quantity;

    public Order() {}

    public Order(Long id, Long user_id, String product, String status, int quantity) {
        this.id = id;
        this.user_id = user_id;
        this.product = product;
        this.status = status;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId(){
        return user_id;
    }
    public void setUserId(Long userId) {
        this.user_id = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
