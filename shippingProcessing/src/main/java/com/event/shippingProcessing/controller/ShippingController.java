package com.event.shippingProcessing.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.event.shippingProcessing.DTO.Shipment;
import com.event.shippingProcessing.DTO.ShippingResponse;
import com.event.shippingProcessing.Exception.ShipmentException;
import com.event.shippingProcessing.model.Shipping;
import com.event.shippingProcessing.service.ShippingService;
import org.springframework.http.ResponseEntity;



@RequestMapping("/event")
@RestController
public class ShippingController {

    private ShippingService service;

    public ShippingController(ShippingService service) {
        this.service = service;
    }

    @GetMapping("/shipment")
    public ResponseEntity<List<Shipping>> getAll() throws ShipmentException{
        List<Shipping> shipment = service.getAll();
        if(shipment != null) {
            return ResponseEntity.ok(shipment);
        }
        else{
            throw new ShipmentException("Shipments not available");
        }
    }
    
    @GetMapping("/shipment/{shippingId}")
    public ResponseEntity<Shipping> getById(@PathVariable Integer shippingId) throws ShipmentException {
        Shipping shipment = service.getById(shippingId); 
        if(shipment != null) {
            return ResponseEntity.ok(shipment);
        }
        else{
            throw new ShipmentException("Shipment info for this id not found.");
        }
    }

    @PostMapping("/shipping")
    public ResponseEntity<ShippingResponse> addShipment(@RequestBody Shipment shipment)throws ShipmentException {
        int shippingId = service.AddShipping(shipment);
        if(shippingId > 0) {
            ShippingResponse res = new ShippingResponse(shippingId, "Shipment added");
            return ResponseEntity.ok(res);
        } 
        else{
            throw new ShipmentException("Failed to add shipment");
        }
    }

     
    @PutMapping("/shipment/{shipmentId}")
    public ResponseEntity<String> updateStatus(@PathVariable Integer shippingId, @RequestParam String orderStatus) throws ShipmentException {
        Boolean isUpdated = service.updateStatus(shippingId, orderStatus);
        if(isUpdated) {
            return ResponseEntity.ok("Updated Successfully");
        }
        else{
            throw new ShipmentException("Failed to update the order status");
        }
    }

    @DeleteMapping("/shipment/{shipmentId}")
    public ResponseEntity<String> deletePayment(@PathVariable int shipmentId) throws ShipmentException {
        Boolean isDeleted = service.deleteShipment(shipmentId);
        if(isDeleted) {
            return ResponseEntity.ok("Deleted Payment Information");
        }
        else{
            throw new ShipmentException("Failed to deleted payment information");
        }
    }
    
    
}
