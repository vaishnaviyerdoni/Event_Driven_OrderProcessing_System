package com.event.shippingProcessing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.event.shippingProcessing.model.Shipping;

public interface ShippingDAO extends JpaRepository<Shipping, Integer>{
    
    @SuppressWarnings("null")
    List<Shipping> findAll();

    Shipping findById(int shippingId);
}
