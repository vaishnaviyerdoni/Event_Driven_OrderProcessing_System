package com.event.orderProcessing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.event.orderProcessing.model.Order;

public interface OrderDAO extends JpaRepository<Order, Integer>{
    
    @SuppressWarnings("null")
    List<Order> findAll(); //returns the list of all orders in the db

    Order findById(int orderId); //returns the order by its orderId

    @Query("SELECT O FROM Order O WHERE O.orderStatus = ?1")
    List<Order> findByOrderStatus(String orderStatus); //returns orders by their status

    Double findPriceByOrderId(int orderId); 
}
