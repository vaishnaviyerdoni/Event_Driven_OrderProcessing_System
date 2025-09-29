package com.event.shared_events.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Integer orderId;
    private Integer itemId;
    private int quantity;

    public OrderItem() {

    }

    public OrderItem (Integer orderId, Integer itemId, int quantity){
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
