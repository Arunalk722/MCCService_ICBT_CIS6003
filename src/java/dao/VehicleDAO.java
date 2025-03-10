/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
/**
 *
 * @author Aruna
 */
public class VehicleDAO {
 public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        String query = "SELECT vehicle_id,vehicle_number,vehicle_type FROM vehicles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicle_id"),
                        rs.getString("vehicle_number"),
                        rs.getString("vehicle_type")
                );
                vehicleList.add(vehicle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleList;
    }
}