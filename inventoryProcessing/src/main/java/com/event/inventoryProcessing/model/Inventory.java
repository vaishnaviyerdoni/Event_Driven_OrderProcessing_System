package com.event.inventoryProcessing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Inventory {
    
    @Id
    @GeneratedValue
    private Integer itemid;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int quantity;
}
