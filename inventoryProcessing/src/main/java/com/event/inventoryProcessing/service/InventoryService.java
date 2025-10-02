package com.event.inventoryProcessing.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import com.event.inventoryProcessing.DTO.AddItem;
import com.event.inventoryProcessing.Exceptions.ItemNotFoundException;
import com.event.inventoryProcessing.model.Inventory;
import com.event.inventoryProcessing.repository.InventoryDAO;

@Service
public class InventoryService {
    
    private InventoryDAO inventoryDAO;
    
    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    //POST method - To add new Item in Inventory
    public int addItem(AddItem item) {
        try {
            String itemname = item.getItemname();
            String category = item.getCategory();
            Double price = item.getPrice();
            int quantity = item.getQuantity();
            int LowStockThreshold = item.getLowStockThreshold();

            Inventory myItem = new Inventory(itemname, category, price, quantity, LowStockThreshold);
            Inventory newItem = inventoryDAO.save(myItem);
            return newItem.getItemId();
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //GET method - To get all items in inventory
    public List<Inventory> getAllItems() {
        try{
            List<Inventory> items = inventoryDAO.findAll();
            if(items != null) {
                return items;
            }
            else{
                throw new ItemNotFoundException("Items are unavailable!");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //GET method - To get item by its Id
    public Inventory getItemById(int itemId) {
        try{
            Inventory item = inventoryDAO.findById(itemId);
            if(item != null) {
                return item;
            }
            else{
                throw new ItemNotFoundException("Item not found");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new Inventory();
        }
    }

    //GET method - To get items by category
    public List<Inventory> getbyCategory(String category) {
        try{
            List<Inventory> items = inventoryDAO.findByCategory(category);
            if(items != null) {
                return items;
            }
            else{
                throw new ItemNotFoundException("Items for this category are absent");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //GET method - To get Price by ItemId
    public Double getPricebyId(Integer itemId) {
        try{
            Double price = inventoryDAO.findPriceById(itemId);
            if(price != null){
                return price;
            }
            else{
                throw new ItemNotFoundException("Price for this item could not be fetched");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return 0.0D;
        }
    }

    //To check if inventory has enough stock
    public boolean isAvailable(int itemId) {
        try{
            Inventory item = inventoryDAO.findById(itemId);
            if(item.getQuantity() >  item.getLowStockThreshold()) {
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Update method - To update the quantity of Item
    public boolean updateStock(int itemId, int newQuantity) {
        try{
            Inventory item = inventoryDAO.findById(itemId);
            
            if(!isAvailable(itemId)) {
                item.setQuantity(newQuantity);
                inventoryDAO.save(item);
                return true;
            }
            else{
                throw new ItemNotFoundException("Stock is available for this item");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //DELETE method - To delete item from inventory
    public boolean deleteItem(int itemId) {
        try{
            Inventory item = inventoryDAO.findById(itemId);
            inventoryDAO.delete(item);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
