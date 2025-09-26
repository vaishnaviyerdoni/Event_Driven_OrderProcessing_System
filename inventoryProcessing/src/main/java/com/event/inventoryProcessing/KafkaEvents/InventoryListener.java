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

    public void consumeOrder(OrderPlaced order) {

        System.out.println("Event received from OrderPublisher for orderId: " + order.getOrderId()); 
        List<OrderItem> items = order.getItems();
        boolean isAvailable = items.stream().allMatch(item -> service.isAvailable(item.getItemId()));
           
        InventoryEvent event = new InventoryEvent();

        event.setOrderId(order.getOrderId());
        event.setOrderStatus("Pending");
        event.setItems(items);
        event.setTotalAmount(order.getTotalAmount());

        if(isAvailable){
            event.setInventoryStatus("InventoryReserved");
            publisher.sendInventoryMessage(event);
        }
        else{
            event.setInventoryStatus("InventoryFailed");
            publisher.sendInventoryMessage(event);
        }
    }
}
