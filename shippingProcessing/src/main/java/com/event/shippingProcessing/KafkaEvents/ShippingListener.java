package com.event.shippingProcessing.KafkaEvents;

import org.springframework.kafka.annotation.KafkaListener;
import com.event.shared_events.dto.PaymentEvent;
import com.event.shared_events.dto.ShippingDetails;
import com.event.shippingProcessing.DTO.Shipment;
import com.event.shippingProcessing.service.ShippingService;

public class ShippingListener {
    private final ShippingService service;
    private final ShippingPublisher publisher;

    public ShippingListener(ShippingService service, ShippingPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @KafkaListener(topics = "${General.payment.kafka-topic}", groupId = "shippingProcessing")
    public void consumePayment(PaymentEvent event) {

        ShippingDetails details = new ShippingDetails();
        details.setAmount(event.getAmount());
        details.setItems(event.getItems());
        details.setOrderId(event.getOrderId());
        details.setAddress(event.getAddress());
    
        Shipment shipment = new Shipment("", event.getOrderStatus());
        
        if("PaymentSuccessful".equals(event.getPaymentStatus())) {
            details.setOrderStatus("OrderShipped");
            details.setShippingStatus("ShippedSuccessfully");

            service.AddShipping(shipment);
            publisher.sendShippingMessaage(details);
        }
        else{
            details.setOrderStatus("OrderFailed");
            details.setShippingStatus("ShippingFailed");

            publisher.sendShippingMessaage(details);
        }
    }
}
