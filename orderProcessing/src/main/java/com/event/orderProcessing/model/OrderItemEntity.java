package com.event.orderProcessing.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter @Setter
public class OrderItemEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Integer id;

    @JoinColumn(name="orderId")
    private Order order;

    @Column(name = "itemId", nullable = false)
    private Integer itemId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    public OrderItemEntity(){

    }

    public OrderItemEntity(Order order, Integer itemId, int quantity, Double subtotal) {
        this.order = order;
        this.itemId = itemId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}
