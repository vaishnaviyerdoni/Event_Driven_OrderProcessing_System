package com.event.paymentProcessing.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakePayment {
    private Double amount;
    private String orderStatus;
    private String address;

    public MakePayment() {

    }

    public MakePayment(Double amount, String orderStatus, String address) {
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
