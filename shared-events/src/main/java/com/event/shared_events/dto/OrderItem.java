package com.event.shared_events.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Integer itemId;
    private String itemname;
    private int quantity;

    public OrderItem() {

    }

    public OrderItem (Integer itemId, String itemname, int quantity) {
        this.itemId = itemId;
        this.itemname = itemname;
        this.quantity = quantity;
    }
}
