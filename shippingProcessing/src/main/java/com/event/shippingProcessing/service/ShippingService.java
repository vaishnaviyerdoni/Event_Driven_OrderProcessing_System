package com.event.shippingProcessing.service;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.event.shippingProcessing.DTO.Shipment;
import com.event.shippingProcessing.Exception.ShipmentException;
import com.event.shippingProcessing.model.Shipping;
import com.event.shippingProcessing.repository.ShippingDAO;

@Service
public class ShippingService {
    
    private ShippingDAO shippingDAO;

    public ShippingService(ShippingDAO shippingDAO) {
        this.shippingDAO = shippingDAO;
    }

    //POST - Add Shipping Information
    public int AddShipping(Shipment shipment) {
        try{
            String address = shipment.getAddress();
            String status = shipment.getOrderStatus();

            Shipping ship = new Shipping(address, status);
            Shipping newShipment = shippingDAO.save(ship);
            return newShipment.getShippingId();
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //GET - To get Shipping Information
    public List<Shipping> getAll() {
        try{
            List<Shipping> allShips = shippingDAO.findAll();
            if(allShips != null) {
                return allShips;
            }
            else{
                throw new ShipmentException("Information unavailable!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //Get - To get shipping information by shippingId
    public Shipping getById(int shippingId) {
        try{
            Shipping shipment = shippingDAO.findByShippingId(shippingId);
            if(shipment != null) {
                return shipment;
            }
            else{
                throw new ShipmentException("Information for this id is not available.");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new Shipping();
        }
    }

    //Update - To update the orderstatus
    public boolean updateStatus(int shippingId, String orderStatus) {
        try{
            Shipping shipping = shippingDAO.findByShippingId(shippingId);
            shipping.setOrderStatus(orderStatus);
            shippingDAO.save(shipping);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Delete - To delete a shipment info
    public boolean deleteShipment(int shippingId) {
        try{
            Shipping shipping = shippingDAO.findByShippingId(shippingId);
            shippingDAO.delete(shipping);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
