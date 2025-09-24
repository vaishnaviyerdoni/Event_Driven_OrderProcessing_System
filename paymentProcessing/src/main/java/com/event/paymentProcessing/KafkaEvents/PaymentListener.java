package com.event.paymentProcessing.KafkaEvents;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.event.paymentProcessing.service.PaymentService;
import com.event.shared_events.dto.*;

@Component
public class PaymentListener {
    private final PaymentService service;

    public PaymentListener(PaymentService service) {
        this.service = service;
    }

    @KafkaListener(topics = "${general.inventory.kafka-topic}", groupId = "paymentProcessing")
    public void consumeInventory(String message, PaymentEvent event){
        
    }
}
