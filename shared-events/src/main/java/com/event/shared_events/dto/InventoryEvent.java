package com.event.shared_events.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryEvent {
    private Integer orderId;
    private String inventoryStatus; //InventoryReserved Or InventoryFailed depending on stock
    private String orderStatus; //Order status by default is pending
    private List<OrderItem> items; //List containing Order items details

    public InventoryEvent() {

    }

    public InventoryEvent(Integer orderId, String inventoryStatus, String orderStatus, List<OrderItem> items) {
        this.orderId = orderId;
        this.inventoryStatus = inventoryStatus;
        this.items = items;
        this.orderStatus = orderStatus;
    }
}
