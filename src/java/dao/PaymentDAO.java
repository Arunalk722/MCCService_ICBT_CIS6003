/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Payment;
import java.sql.*;

/**
 *
 * @author Aruna
 */
public class PaymentDAO {
    
 public boolean validateBookingBeforePayment(int bookingId, String customerPhone) {
        String query = "SELECT * FROM bookings WHERE booking_id = ? AND customer_phone = ? AND is_paid = FALSE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            stmt.setString(2, customerPhone);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean processPayment(Payment payment) {
        String paymentQuery = "INSERT INTO payments (booking_id, customer_phone, amount, payment_method, payment_date) VALUES (?, ?, ?, ?, NOW())";
        String updateBookingQuery = "UPDATE bookings SET is_paid = TRUE WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmtPayment = conn.prepareStatement(paymentQuery);
             PreparedStatement stmtUpdateBooking = conn.prepareStatement(updateBookingQuery)) {

            stmtPayment.setInt(1, payment.getBookingId());
            stmtPayment.setString(2, payment.getCustomerPhone());
            stmtPayment.setFloat(3, payment.getAmount());
            stmtPayment.setString(4, payment.getPaymentMethod());

            int paymentInserted = stmtPayment.executeUpdate();

            if (paymentInserted > 0) {
                stmtUpdateBooking.setInt(1, payment.getBookingId());
                int bookingUpdated = stmtUpdateBooking.executeUpdate();
                return bookingUpdated > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
