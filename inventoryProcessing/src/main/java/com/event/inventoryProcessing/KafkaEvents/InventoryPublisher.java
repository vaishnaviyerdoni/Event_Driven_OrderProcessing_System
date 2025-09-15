package com.event.inventoryProcessing.KafkaEvents;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryPublisher {
    
    private final KafkaTemplate<String, String> kafkaTemp;

    public InventoryPublisher(KafkaTemplate<String, String> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void sendInventoryMessage(String message) {
        try{
            kafkaTemp.send("Message", message);
            System.out.println("Message Received From Listener: " + message);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
