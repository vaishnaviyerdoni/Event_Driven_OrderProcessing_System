package com.event.inventoryProcessing.KafkaEvents;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryListener {

    private final InventoryPublisher publisher;

    public InventoryListener(InventoryPublisher publisher) {
        this.publisher = publisher;
    }
    
    //this method listens message from Order, verifies the inventory and call Publisher
    //Passes Event String to publisher
    @KafkaListener(topics = "${general.order.kafka-topic}", groupId = "inventoryProcessing")

    public void consumeOrder(String message) {

        System.out.println("Event received from OrderPublisher: " + message);

        if(message.equals("OrderPlaced")) {
            
            
            publisher.sendInventoryMessage("InventoryReserved");
        }
        else{
            publisher.sendInventoryMessage("InventoryFailed");
        }
    }
}
