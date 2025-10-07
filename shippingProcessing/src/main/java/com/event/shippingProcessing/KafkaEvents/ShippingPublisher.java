package com.event.shippingProcessing.KafkaEvents;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.event.shared_events.dto.ShippingDetails;

@Component
public class ShippingPublisher {
    private final KafkaTemplate<String, Object> kafkaTemp;

    public ShippingPublisher(KafkaTemplate<String, Object> kafkaTemp) {
        this.kafkaTemp = kafkaTemp;
    }

    public void sendShippingMessaage(ShippingDetails event) {
        kafkaTemp.send(event.getShippingStatus(), event);
    }
}
