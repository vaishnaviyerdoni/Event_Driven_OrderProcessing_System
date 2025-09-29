package com.event.shared_events.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class OrderPlaced {
    private Integer orderId;
    private List<OrderItem> items;
    private String orderStatus;
    private Double totalAmount;
    private String address;
}
