package com.event.paymentProcessing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.event.paymentProcessing.model.Payment;

public interface PaymentDAO extends JpaRepository<Payment, Integer>{
    
    @SuppressWarnings("null")
    List<Payment> findAll();

    Payment findById(int paymentId);
}
