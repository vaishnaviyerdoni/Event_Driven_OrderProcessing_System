package com.event.inventoryProcessing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.event.inventoryProcessing.model.Inventory;

public interface InventoryDAO extends JpaRepository<Inventory, Integer>{
    @SuppressWarnings("null")
    List<Inventory> findAll(); // returns list of inventory items

    Inventory findById(int itemId); // returns an inventory item by its id

    Inventory findByCategory(String category); //returns an item by its category

    Double findPriceByItemname(String itemname); //return item price by the item name
}
