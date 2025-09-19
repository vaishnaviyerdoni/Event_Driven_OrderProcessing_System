package com.event.paymentProcessing.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakePayment {
    private Double amount;
    private String orderStatus;

    public MakePayment() {

    }

    public MakePayment(Double amount, String orderStatus) {
        this.amount = amount;
        this.orderStatus = orderStatus;
    }
}
