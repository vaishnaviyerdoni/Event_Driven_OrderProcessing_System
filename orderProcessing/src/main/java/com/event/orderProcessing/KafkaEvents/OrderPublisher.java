package com.event.orderProcessing.KafkaEvents;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.event.shared_events.dto.*;

@Component
public class OrderPublisher { // Publishes event to InventoryListener
    
    private final KafkaTemplate<String, Object> kafkaTemp;

    public OrderPublisher(KafkaTemplate<String, Object> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void placeOrder(String message, OrderPlaced order) {
        kafkaTemp.send("OrderPlaced", order);
        System.out.println("Message Received:" + message);
    }
}
