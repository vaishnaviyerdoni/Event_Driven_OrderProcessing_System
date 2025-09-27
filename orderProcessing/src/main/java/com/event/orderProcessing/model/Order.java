package com.event.orderProcessing.model;

import java.time.LocalDate;
import java.util.List;
import com.event.shared_events.dto.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderId")
    private Integer orderId;

    @Column(name="orderDate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "orderStatus", nullable = false)
    private String orderStatus;

    @Column(name="totalAmount", nullable = false)
    private Double totalAmount;

    @Column(name= "address", nullable = false)
    private String address;

    private List<OrderItem> items;
    
    public Order() {

    }

    public Order(LocalDate orderDate, String orderStatus, Double totalAmount, String address) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.address = address;
    }
}
