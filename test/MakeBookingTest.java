/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import dao.BookingDAO;
import dao.DiscountDAO;
import static junit.framework.Assert.fail;
import model.Booking;
import model.DiscountStrategy;
import model.NotificationService;
import model.PercentageDiscount;
import org.junit.Assert;
import org.junit.Test;



public class MakeBookingTest {
    
    public MakeBookingTest() {
    }
    
     @Test
     public void makeBooking() {
        String customerPhone = "0776461602";
        String source = "Kegalla";
        String destination = "Avissawella";
        int vehicleId = 1;
        int driverId = 1;
        float totalAmount =2500;
        String discountCode = "NA";        
        // Get Discount Percentage
        DiscountDAO discountDAO = new DiscountDAO();
        float discountPercentage = discountDAO.getDiscountPercentage(discountCode);
        // Apply Discount
        DiscountStrategy discountStrategy = new PercentageDiscount(discountPercentage);
        float finalAmount = discountStrategy.applyDiscount(totalAmount);
        // Create Booking
        Booking booking = new Booking(source, destination, customerPhone, vehicleId, driverId, finalAmount);
        BookingDAO bookingDAO = new BookingDAO();
        int bookingId = bookingDAO.createBooking(booking);   
        NotificationService noti = new NotificationService();
        noti.sendBookingConfirmation(bookingId, customerPhone);
        Assert.assertTrue("Booking creating failed",bookingId > 0);
     }    
     
     @Test
     public void makeBookingWithDiscount() {
        String customerPhone = "0776461601";
        String source = "Kegalla-VIP";
        String destination = "Avissawella-VIP";
        int vehicleId = 1;
        int driverId = 1;
        float totalAmount =25000;
        String discountCode = "VIP20";        
        // Get Discount Percentage
        DiscountDAO discountDAO = new DiscountDAO();
        float discountPercentage = discountDAO.getDiscountPercentage(discountCode);
        // Apply Discount
        DiscountStrategy discountStrategy = new PercentageDiscount(discountPercentage);
        float finalAmount = discountStrategy.applyDiscount(totalAmount);
        // Create Booking
        Booking booking = new Booking(source, destination, customerPhone, vehicleId, driverId, finalAmount);
        BookingDAO bookingDAO = new BookingDAO();
        int bookingId = bookingDAO.createBooking(booking);   
        NotificationService noti = new NotificationService();
        noti.sendBookingConfirmation(bookingId, customerPhone);
        Assert.assertTrue("Booking creating failed",totalAmount > finalAmount);
     }
}
