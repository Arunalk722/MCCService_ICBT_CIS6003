/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aruna
 */

public class BookingDAO {

    
    public int createBooking(Booking booking) {
        int bookingId = -1;
        String query = "INSERT INTO bookings (customer_phone, source, destination, vehicle_id, driver_id, total_amount, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, booking.getCustomerPhone());
            stmt.setString(2, booking.getSource());
            stmt.setString(3, booking.getDestination());
            stmt.setInt(4, booking.getVehicleId());
            stmt.setInt(5, booking.getDriverId());
            stmt.setFloat(6, booking.getTotalAmount());
            stmt.setBoolean(7, false);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bookingId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
        }
        return bookingId;
    }
   
    public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        Booking booking = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("customer_phone"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getFloat("total_amount"),
                    rs.getBoolean("is_paid")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching booking: " + e.getMessage());
        }
        return booking;
    }
   
    public boolean markBookingAsPaid(int bookingId) {
        String query = "UPDATE bookings SET is_paid = TRUE WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error marking booking as paid: " + e.getMessage());
            return false;
        }
    }
   
    public boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }
    
    public void autoCancelUnpaidBookings() {
        String query = "DELETE FROM bookings WHERE is_paid = FALSE AND booking_date < NOW() - INTERVAL 2 DAY";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + " unpaid bookings were automatically canceled.");
            }
        } catch (SQLException e) {
            System.err.println("Error auto-canceling bookings: " + e.getMessage());
        }
    }
    
    public List<Booking> getAllBookings() {
        String query = "SELECT * FROM bookings";
        List<Booking> bookingList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("customer_phone"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getFloat("total_amount"),
                    rs.getBoolean("is_paid")
                );
                bookingList.add(booking);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all bookings: " + e.getMessage());
        }
        return bookingList;
    }
    
    public float getBookingAmount(int bookingId) {
        String query = "SELECT total_amount FROM bookings WHERE booking_id = ?";
        float amount = 0.0f;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                amount = rs.getFloat("total_amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }
}
