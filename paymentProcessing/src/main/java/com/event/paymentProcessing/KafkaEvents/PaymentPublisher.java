package com.event.paymentProcessing.KafkaEvents;

import org.springframework.kafka.core.KafkaTemplate;
import com.event.shared_events.dto.*;

public class PaymentPublisher {
    
    private KafkaTemplate<String, Object> kafkaTemp;

    public PaymentPublisher(KafkaTemplate<String, Object> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void sendPaymentMessage(PaymentEvent event){
        kafkaTemp.send(event.getPaymentStatus(), event);
        System.out.println("Message Received From Listener: " + event.getPaymentStatus());
    }
}
