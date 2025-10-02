package com.event.inventoryProcessing.controller;

import com.event.inventoryProcessing.DTO.AddItem;
import com.event.inventoryProcessing.DTO.AddItemRes;
import com.event.inventoryProcessing.Exceptions.ItemNotFoundException;
import com.event.inventoryProcessing.model.Inventory;
import com.event.inventoryProcessing.service.InventoryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/event")
@RestController
public class InventoryController {
    
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory") //To get a list of all Items
    public ResponseEntity<List<Inventory>> getAll() throws ItemNotFoundException {
        List<Inventory> items = inventoryService.getAllItems();
        if(items.isEmpty()) {
            return ResponseEntity.ok(items);
        }
        else{
            throw new ItemNotFoundException("Items were not found");
        }
    }

    @GetMapping("/inventory/{category}")
    public ResponseEntity<List<Inventory>> getByCategory(@PathVariable String category) throws ItemNotFoundException {
        List<Inventory> items = inventoryService.getbyCategory(category);
        if(items.isEmpty()) {
            return ResponseEntity.ok(items);
        }
        else{
            throw new ItemNotFoundException("No items found in this category");
        }
    }
    

    @GetMapping("/inventory/{itemId}") //To get an item by its id
    public ResponseEntity<Inventory> getById(@PathVariable int itemId) throws ItemNotFoundException {
        Inventory item = inventoryService.getItemById(itemId);
        if(item != null) {
            return ResponseEntity.ok(item);
        }
        else{
            throw new ItemNotFoundException("Item for this itemId not found.");
        }
    }
    
    @GetMapping("/inventory/{itemId}") //To get price of an item
    public Double getPrice(@PathVariable int itemId) throws ItemNotFoundException {
        Double price = inventoryService.getPricebyId(itemId);
        if(price != null) {
            return price;
        }
        else{
            throw new ItemNotFoundException("Price for this item could not be fetched");
        }
    }

    @PostMapping("/inventory") //To add new Item (Admin task)
    public ResponseEntity<AddItemRes> postMethodName(@RequestBody AddItem item) throws Exception {
        Integer itemId = inventoryService.addItem(item);
        if (itemId > 0){
            AddItemRes res = new AddItemRes(itemId, "Item added Successfully");
            return ResponseEntity.ok(res); 
        }
        else{
            throw new Exception("Something went try again later");
        }
    }
    
    @PutMapping("/inventory/{itemId}")
    public ResponseEntity<String> updateStock(@PathVariable Integer itemId, @RequestParam int quantity) {
        Boolean isUpdated = inventoryService.updateStock(itemId, quantity);
        if(isUpdated) {
            return ResponseEntity.ok("Stock Updated Successfully");
        }
        else{
            return ResponseEntity.ok("Failed to update Stock, try again later");
        }
    }

    @PutMapping("/inventory/{itemId}")
    public ResponseEntity<String> updatePrice(@PathVariable Integer itemId, @RequestParam Double price) {
        Boolean isUpdated = inventoryService.updatePrice(itemId, price);
        if(isUpdated){
            return ResponseEntity.ok("Price Updated Successfully");
        }
        else{
            return ResponseEntity.ok("Failed to update Price");
        }
    }

    @DeleteMapping("/inventory/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable Integer itemId) {
        Boolean isDeleted = inventoryService.deleteItem(itemId);
        if(isDeleted) {
            return ResponseEntity.ok("Delete Item");
        }
        else{
            return ResponseEntity.ok("Couldn't not delete item.");
        }
    }
}
