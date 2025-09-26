package com.event.shippingProcessing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString
public class Shipping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "shippingId", nullable = false)
    private Integer shippingId;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="orderStatus", nullable = false)
    private String  orderStatus;
   
    public Shipping() {

    }

    public Shipping(String address, String orderStatus) {
        this.address = address;
        this.orderStatus = orderStatus;
    }
}
