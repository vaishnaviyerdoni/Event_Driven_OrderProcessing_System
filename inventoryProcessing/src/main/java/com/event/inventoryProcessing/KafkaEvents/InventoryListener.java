package com.event.inventoryProcessing.KafkaEvents;

import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.event.inventoryProcessing.service.InventoryService;
import com.event.shared_events.dto.*;

@Component
public class InventoryListener { // Listens from OrderPublisher

    private final InventoryPublisher publisher;
    private final InventoryService service;

    public InventoryListener(InventoryPublisher publisher, InventoryService service) {
        this.publisher = publisher;
        this.service = service;
    }
    
    //this method listens message from Order, verifies the inventory and call Publisher
    //Passes Event String to publisher
    @KafkaListener(topics = "${general.order.kafka-topic}", groupId = "inventoryProcessing")

    public void consumeOrder(String message, OrderPlaced order) {

        System.out.println("Event received from OrderPublisher: " + message);

        if(message.equals("OrderPlaced")) {
            
           List<OrderItem> items = order.getItems();
           boolean isAvailable = items.stream().allMatch(item -> service.isAvailable(item.getItemId()));
           
            InventoryEvent event = new InventoryEvent();

            if(isAvailable){
                event.setOrderId(order.getOrderId());
                event.setInventoryStatus("InventoryReserved");
                event.setOrderStatus("Pending");
                event.setItems(items);

                publisher.sendInventoryMessage("InventoryReserved", event);
            }
            else{
                event.setOrderId(order.getOrderId());
                event.setInventoryStatus("InventoryFailed");
                event.setOrderStatus("Pending");
                event.setItems(items);

                publisher.sendInventoryMessage("InventoryFailed", event);
            }
        }
    }
}
