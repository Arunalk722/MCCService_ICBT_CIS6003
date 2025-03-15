/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Aruna
 */
interface Observer {
    void update(String message);
}
public class NotificationService {
    private List<Observer> observers = new ArrayList<>();
    private List<String> notifications = new ArrayList<>(); 
    

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

 
    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message); 
        }
    }

    // Send booking confirmation
    public void sendBookingConfirmation(int bookingId, String customerPhone) {
        String message = "Booking " + bookingId + " confirmed for " + customerPhone;
        notifications.add(message);
        System.out.println("Booking confirmation sent to " + customerPhone);
        System.out.println("http://localhost:8080/Mega_City_Cab_Service/verifyPayment.jsp?bookingId=" + bookingId);
        
        // Notify observers
        notifyObservers(message);
    }

    // Send payment failure
    public void sendPaymentFailure(int bookingId, String customerPhone) {
        String message = "Payment failed for Booking " + bookingId + ". Contact support.";
        notifications.add(message);
        System.out.println("Payment failure notification sent to " + customerPhone);
        
        // Notify observers
        notifyObservers(message);
    }
}