package com.event.inventoryProcessing.controller;

import org.springframework.web.bind.annotation.RestController;
import com.event.inventoryProcessing.Exceptions.ItemNotFoundException;
import com.event.inventoryProcessing.model.Inventory;
import com.event.inventoryProcessing.service.InventoryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/event")
@RestController
public class InventoryController {
    
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<Inventory>> getAll() throws ItemNotFoundException {
        List<Inventory> items = inventoryService.getAllItems();
        if(items != null) {
            return ResponseEntity.ok(items);
        }
        else{
            throw new ItemNotFoundException("Items were not found");
        }
    }
    
}
