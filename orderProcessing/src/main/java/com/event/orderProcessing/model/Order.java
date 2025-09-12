package com.event.orderProcessing.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
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
}
