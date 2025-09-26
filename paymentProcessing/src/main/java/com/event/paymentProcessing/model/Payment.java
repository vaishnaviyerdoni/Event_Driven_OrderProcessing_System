package com.event.paymentProcessing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@Table(name="payment")
@Entity
@ToString
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="paymentId", nullable = false)
    private Integer paymentId;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name="orderStatus", nullable = false)
    private String orderStatus;

    @Column(name="address", nullable = false)
    private String address;

    public Payment() {

    }

    public Payment(Double amount, String orderStatus, String address) {
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
