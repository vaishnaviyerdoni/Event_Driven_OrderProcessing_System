package com.event.orderProcessing.controller;

import org.springframework.web.bind.annotation.RestController;
import com.event.orderProcessing.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class OrderController {
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
