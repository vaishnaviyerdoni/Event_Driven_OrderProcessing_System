package com.event.inventoryProcessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.event.inventoryProcessing.model.Inventory;

public interface InventoryDAO extends JpaRepository<Integer, Inventory>{
    
}
