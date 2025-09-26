package com.event.inventoryProcessing.KafkaEvents;

import com.event.shared_events.dto.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryPublisher { //Publishes event to PaymentListener
    
    private final KafkaTemplate<String, Object> kafkaTemp;

    public InventoryPublisher(KafkaTemplate<String, Object> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void sendInventoryMessage(InventoryEvent event) {
        try{
            kafkaTemp.send(event.getInventoryStatus(), event);
            System.out.println("Message Received From Listener: " + event.getInventoryStatus()); //message == InventoryReserved OR InventoryFailed
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
