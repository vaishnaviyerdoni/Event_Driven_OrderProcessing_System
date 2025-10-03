package com.event.shippingProcessing.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingResponse {
    private Integer shippingId;
    private String message;
    
} 