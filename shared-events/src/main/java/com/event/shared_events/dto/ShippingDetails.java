package com.event.shared_events.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetails {
    private Integer orderId;
    private List<OrderItem> items;
    private Double amount;
    private String orderStatus;
    private String shippingStatus;
    private String address;
}
