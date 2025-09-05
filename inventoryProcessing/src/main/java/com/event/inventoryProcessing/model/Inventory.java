package com.event.inventoryProcessing.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    
    @Id
    @GeneratedValue
    private long itemid;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean isAvailable;

}
