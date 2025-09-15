package com.event.inventoryProcessing.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddItem {
    private String itemname;
    private String category;
    private Double price;
    private int quantity;
    private int LowStockThreshold;

    public AddItem() {

    }

    public AddItem(String itemname, String category, Double price, int quantity, int LowStockThreshold) {
        this.itemname = itemname;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.LowStockThreshold = LowStockThreshold;
    }
}
