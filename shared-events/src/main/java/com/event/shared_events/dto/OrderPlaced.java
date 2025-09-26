package com.event.shared_events.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlaced {
    private Integer orderId;
    private List<OrderItem> items;
    private String orderStatus;
    private Double totalAmount;
    private String address;

    public OrderPlaced() {

    }

    public OrderPlaced(Integer orderId, List<OrderItem> items, String orderStatus, Double totalAmount, String address) {
        this.orderId = orderId;
        this.items = items;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.address = address;
    }
}
