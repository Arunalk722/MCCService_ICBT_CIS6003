/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aruna
 */
public class DriverDAO {
    
    public List<Driver> getAllDrivers() {
        List<Driver> driverList = new ArrayList<>();
        String query = "SELECT driver_id, name, license_number FROM drivers WHERE status = 'available'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("driver_id"),
                        rs.getString("name"),
                        rs.getString("license_number")
                );
                driverList.add(driver);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return driverList;
    }
}
