package com.event.shared_events.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEvent {
    private Integer orderId;
    private String inventoryStatus; //InventoryReserved Or InventoryFailed depending on stock
    private String orderStatus; //Order status by default is pending
    private List<OrderItem> items; //List containing Order items details
    private Double totalAmount; //total amount of an order
    private String address; //Shipping address
}