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

    public OrderPlaced() {

    }

    public OrderPlaced(Integer orderId, List<OrderItem> items, String orderStatus) {
        this.orderId = orderId;
        this.items = items;
        this.orderStatus = orderStatus;
    }
}
