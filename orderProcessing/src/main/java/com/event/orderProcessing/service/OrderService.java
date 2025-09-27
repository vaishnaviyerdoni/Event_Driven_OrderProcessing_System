package com.event.orderProcessing.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.event.orderProcessing.DTO.AddOrder;
import com.event.orderProcessing.Exception.OrderNotFoundException;
import com.event.orderProcessing.KafkaEvents.OrderPublisher;
import com.event.orderProcessing.model.Order;
import com.event.orderProcessing.repository.OrderDAO;
import com.event.shared_events.dto.*;

@Service
public class OrderService {
    
    private OrderDAO orderDAO;
    private OrderPublisher publisher;

    public OrderService(OrderDAO orderDAO, OrderPublisher publisher) {
        this.orderDAO = orderDAO;
        this.publisher = publisher;
    }

    //POST method : To place an Order
    public int placeOrder(AddOrder order) {
        try{
            LocalDate orderDate = order.getOrderDate();
            String orderStatus = order.getOrderStatus();
            Double totalAmount = order.getTotalAmount();
            String address = order.getAddress();

            Order myOrder = new Order(orderDate, orderStatus, totalAmount, address);
            Order newOrder = orderDAO.save(myOrder);

            //Publishing Kafka Event
            OrderPlaced event = new OrderPlaced(newOrder.getOrderId(), newOrder.getItems() ,newOrder.getOrderStatus(), newOrder.getTotalAmount(), newOrder.getAddress());
            publisher.placeOrder(event);

            return newOrder.getOrderId();
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //GET method : To get all the Orders
    public List<Order> getAllOrders() {
        try{
            List<Order> orders = orderDAO.findAll();
            if(orders != null) {
                return orders;
            }
            else{
                throw new OrderNotFoundException("Orders are absent");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //GET method : To get Order by its Id
    public Order getById(int orderId) {
        try{
            Order order = orderDAO.findById(orderId);
            if(order != null) {
                return order;
            }
            else{
                throw new OrderNotFoundException("Order not found!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new Order();
        }
    }

    //GET method : To get Orders by status
    public List<Order> getOrderbyStatus(String status){
        try{
            List<Order> orders = orderDAO.findByOrderStatus(status);
            if(orders != null) {
                return orders;
            }
            else{
                throw new OrderNotFoundException("Order for this status not found!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //GET method : To get Price of order
    public Double getPrice(int orderId) {
        Double price = 0.0d;
        try{
            price = orderDAO.findPriceByOrderId(orderId);
            if(price != null) {
                return price;
            }
            else{
                throw new OrderNotFoundException("Price for this Order is absent");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return price;
        }
    }

    //UPDATE method : To Update the orderStatus
    public boolean updateStatus(int orderId, String newOrderStatus) {
        try{
            Order order = orderDAO.findById(orderId);
            order.setOrderStatus(newOrderStatus);
            orderDAO.save(order);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    //DELETE method : To Delete an Order
    public boolean deleteOrder(int orderId) {
        try{
            Order order = orderDAO.findById(orderId);
            orderDAO.delete(order);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
