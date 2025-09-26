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
public class PaymentEvent {
    private Integer orderId;
    private List<OrderItem> items;
    private Double amount;
    private String orderStatus;
    private String paymentStatus;
    private String address;
}
