package com.event.paymentProcessing.controller;

import org.springframework.web.bind.annotation.RestController;

import com.event.paymentProcessing.DTO.MakePayment;
import com.event.paymentProcessing.DTO.PaymentResponse;
import com.event.paymentProcessing.Exception.PaymentException;
import com.event.paymentProcessing.model.Payment;
import com.event.paymentProcessing.service.PaymentService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/event")
@RestController
public class PaymentController {
    
    private PaymentService service;                       

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> getAll(@RequestParam String param) throws PaymentException {
        List<Payment> payment = service.getAll();
        if(payment != null) {
            return ResponseEntity.ok(payment);
        }
        else{
            throw new PaymentException("Payment Information not found!");
        }
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getById(@PathVariable Integer paymentId) throws PaymentException {
        Payment payment = service.getById(paymentId);
        if(payment != null) {
            return ResponseEntity.ok(payment);
        }
        else{
            throw new PaymentException("Payment for this id not found");
        }
    }
    
    @PostMapping("/payment")
    public ResponseEntity<PaymentResponse> postPayment(@RequestBody MakePayment payment) throws PaymentException {
        int paymentId = service.payOrder(payment);
        PaymentResponse res = new PaymentResponse(paymentId, "Payment Successful");
        if(paymentId > 0) {
            return ResponseEntity.ok(res);
        }
        else{
            throw new PaymentException("Payment Failed");
        }
    }
    
    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<String> updateStatus(@PathVariable Integer paymentId, @RequestParam String orderStatus) throws PaymentException {
        Boolean isUpdated = service.updateStatus(paymentId, orderStatus);
        if(isUpdated) {
            return ResponseEntity.ok("Updated Successfully");
        }
        else{
            throw new PaymentException("Failed to update the order status");
        }
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable int paymentId) throws PaymentException {
        Boolean isDeleted = service.deletePayment(paymentId);
        if(isDeleted) {
            return ResponseEntity.ok("Deleted Payment Information");
        }
        else{
            throw new PaymentException("Failed to deleted payment information");
        }
    }
    
}
