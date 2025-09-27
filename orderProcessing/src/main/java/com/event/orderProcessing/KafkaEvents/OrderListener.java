package com.event.orderProcessing.KafkaEvents;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.event.orderProcessing.service.OrderService;
import com.event.shared_events.dto.ShippingDetails;

@Component
public class OrderListener { //Listens to ShippingPublisher
    private final OrderService service;

    public OrderListener(OrderService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${General.shipping.kafka-topics}", groupId = "orderProcessing")
    public void consumeShipping(ShippingDetails details) {
        if("OrderShipped".equals(details.getOrderStatus())){
            boolean isUpdated = service.updateStatus(details.getOrderId(), details.getOrderStatus());
            if(isUpdated) {
                System.out.println(details.getOrderStatus());
            }
            else{
                System.out.println("Failed to update status, order Failed");
            }
        }
        else{
            service.updateStatus(details.getOrderId(), details.getOrderStatus());
            System.out.println(details.getOrderStatus());
        }
    }
}
