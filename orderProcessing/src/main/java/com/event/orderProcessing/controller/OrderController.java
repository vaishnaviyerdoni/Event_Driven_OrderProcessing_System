package com.event.orderProcessing.controller;

import com.event.orderProcessing.DTO.AddOrder;
import com.event.orderProcessing.DTO.OrderResponse;
import com.event.orderProcessing.Exception.OrderNotFoundException;
import com.event.orderProcessing.model.Order;
import com.event.orderProcessing.service.OrderService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/event")
@RestController
public class OrderController {
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")//To get All Orders (Admin Task)
    public ResponseEntity<List<Order>> getAll() throws OrderNotFoundException {
        List<Order> orders = service.getAllOrders();
        if(orders != null){
            return ResponseEntity.ok(orders);
        }
        else{
            throw new OrderNotFoundException("Failed to fetch Orders");
        }
    }

    @GetMapping("/orders/{orderId}")//To get an Order by its id
    public ResponseEntity<Order> getById(@PathVariable int orderId) throws OrderNotFoundException {
        Order order = service.getById(orderId);
        if(order != null){
            return ResponseEntity.ok(order);
        }
        else{
            throw new OrderNotFoundException("Failed to fetch Order for this id");
        }
    }
    
    @GetMapping("/orders/status")//To get Orders by their Statuses(Admin task)
    public ResponseEntity<List<Order>> getByStatus(@RequestParam String orderStatus) throws OrderNotFoundException {
        List<Order> orders = service.getOrderbyStatus(orderStatus);
        if(orders.isEmpty()){
            throw new OrderNotFoundException("Failed to fetch with this status");
        }
        else{
            return ResponseEntity.ok(orders);
        }
    }
    
    @GetMapping("/orders/{orderId}/price")//To get Order Price
    public ResponseEntity<Double> getPrice(@PathVariable int orderId) throws OrderNotFoundException{
        Double price = service.getPrice(orderId);
        if(price > 0){
            return ResponseEntity.ok(price);
        }
        else{
            throw new OrderNotFoundException("Price for this not available");
        }
    }
    
    @PostMapping("/orders") //To place Order
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody AddOrder order) throws Exception {
        int orderId = service.placeOrder(order);
        if(orderId > 0){
            OrderResponse res = new OrderResponse(orderId, "Order Placed Successfully");
            return ResponseEntity.ok(res);
        }
        else{
            throw new Exception("Failed to place Order");
        }
    }

    @PutMapping("/orders/{orderId}/status")//To update Order status(Admin Task)
    public ResponseEntity<String> updateStatus(@PathVariable int orderId, @RequestParam String orderStatus) {
       Boolean isUpdated = service.updateStatus(orderId, orderStatus);
       if(isUpdated){
            return ResponseEntity.ok("Status Updated");
        }
        else{
            return ResponseEntity.ok("Failed to update Status");
        }
    }
    
    @DeleteMapping("/orders/{orderId}")//To delete an Order(Admin task)
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        Boolean isDeleted = service.deleteOrder(orderId);
        if(isDeleted){
            return ResponseEntity.ok("Status Updated");
        }
        else{
            return ResponseEntity.ok("Failed to update Status");
        }
    }
}
