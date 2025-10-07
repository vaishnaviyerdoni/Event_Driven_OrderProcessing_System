package com.event.paymentProcessing.service;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.event.paymentProcessing.DTO.MakePayment;
import com.event.paymentProcessing.Exception.PaymentException;
import com.event.paymentProcessing.model.Payment;
import com.event.paymentProcessing.repository.PaymentDAO;

@Service
public class PaymentService {
    
    private PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    //POST request - To add a Payment
    public int payOrder(MakePayment payment) {
        try{
            Double amt = payment.getAmount();
            String status = payment.getOrderStatus();
            String address = payment.getAddress();

            Payment pay = new Payment(amt, status, address);
            Payment newPay = paymentDAO.save(pay);
            return newPay.getPaymentId();
        }
        catch(Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    //GET request - To get information about Payment information
    public List<Payment> getAll() {
        try{
            List<Payment> payInfo = paymentDAO.findAll();
            if(payInfo != null) {
                return payInfo;
            }
            else{
                throw new PaymentException("Payment Information not available");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    //GET request - To get the payment information by id
    public Payment getById(int paymentId) {
        try{
            Payment payInfo = paymentDAO.findByPaymentId(paymentId);
            if(payInfo != null) {
                return payInfo;
            }
            else{
                throw new PaymentException("Payment information for this id is unavailable.");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new Payment();
        }
    }

    //Update  request - To Update the OrderStatus
    public boolean updateStatus(int paymentId, String newStatus) {
        try{
            Payment payInfo = paymentDAO.findByPaymentId(paymentId);
            payInfo.setOrderStatus(newStatus);
            paymentDAO.save(payInfo);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //Delete request = To Delete payment info by id
    public boolean deletePayment(int paymentId) {
        try{
            Payment pay = paymentDAO.findByPaymentId(paymentId);
            paymentDAO.delete(pay);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}