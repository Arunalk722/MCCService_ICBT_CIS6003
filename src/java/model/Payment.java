/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Aruna
 */
public class Payment {
private int transactionId;
    private int bookingId;
    private String customerPhone;
    private float amount;
    private String paymentMethod;
    private Timestamp paymentDateTime;

   
    public Payment() {}

    
    public Payment(int bookingId, String customerPhone, float amount, String paymentMethod) {
        this.bookingId = bookingId;
        this.customerPhone = customerPhone;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDateTime = new Timestamp(System.currentTimeMillis()); // ✅ Fixed Timestamp Initialization
    }

    
    public Payment(int transactionId, int bookingId, String customerPhone, float amount, String paymentMethod, Timestamp paymentDateTime) {
        this.transactionId = transactionId;
        this.bookingId = bookingId;
        this.customerPhone = customerPhone;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDateTime = paymentDateTime;
    }

    
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Timestamp getPaymentDateTime() { return paymentDateTime; }
    public void setPaymentDateTime(Timestamp paymentDateTime) { this.paymentDateTime = paymentDateTime; }

    
    @Override
    public String toString() {
        return "Payment{" +
                "transactionId=" + transactionId +
                ", bookingId=" + bookingId +
                ", customerPhone='" + customerPhone + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDateTime=" + paymentDateTime +
                '}';
    }
}
