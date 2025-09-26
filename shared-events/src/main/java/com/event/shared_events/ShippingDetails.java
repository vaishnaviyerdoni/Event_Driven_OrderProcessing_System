package com.event.shared_events;

import java.util.List;
import com.event.shared_events.dto.OrderItem;
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
    private String paymentStatus;
}
