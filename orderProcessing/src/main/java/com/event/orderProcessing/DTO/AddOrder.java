package com.event.orderProcessing.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddOrder {
    private LocalDate orderDate;
    private String orderStatus;
    private Double totalAmount;
    private String address;

    public AddOrder() {

    }

    public AddOrder(LocalDate orderDate, String orderStatus, Double totalAmount, String address) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.address = address;
    }
}
