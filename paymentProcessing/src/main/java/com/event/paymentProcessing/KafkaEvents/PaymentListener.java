package com.event.paymentProcessing.KafkaEvents;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.event.paymentProcessing.DTO.MakePayment;
import com.event.paymentProcessing.service.PaymentService;
import com.event.shared_events.dto.*;

@Component
public class PaymentListener {
    private final PaymentService service;
    private final PaymentPublisher publisher;

    public PaymentListener(PaymentService service, PaymentPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @KafkaListener(topics = "${general.inventory.kafka-topic}", groupId = "paymentProcessing")
    public void consumeInventory(InventoryEvent event){

        MakePayment payment =  new MakePayment(event.getTotalAmount(), event.getOrderStatus(), event.getAddress());
        PaymentEvent payEvent = new PaymentEvent();

        payEvent.setAmount(event.getTotalAmount());
        payEvent.setOrderId(event.getOrderId());
        payEvent.setItems(event.getItems());
        payEvent.setOrderStatus(event.getOrderStatus());
        
        if("InventoryReserved".equals(event.getInventoryStatus())){
            service.payOrder(payment);
            payEvent.setPaymentStatus("PaymentSuccessful");

            publisher.sendPaymentMessage(payEvent);
        }
        else{
            payEvent.setPaymentStatus("PaymentFailed");
            publisher.sendPaymentMessage(payEvent);
        }
    }
}
