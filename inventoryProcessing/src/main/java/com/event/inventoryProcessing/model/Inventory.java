package com.event.inventoryProcessing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@Entity
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemId")
    private Integer itemId;

    @Column(name="itemname", nullable = false)
    private String itemname;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Inventory() {

    }

    public Inventory(String itemname, String category, Double price, int quantity) {
        this.itemname = itemname;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}
