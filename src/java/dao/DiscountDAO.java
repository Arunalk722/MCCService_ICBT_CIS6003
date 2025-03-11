/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Aruna
 */
public class DiscountDAO {
    public float getDiscountPercentage(String discountCode) {
        String query = "SELECT discount_percentage FROM discounts WHERE discount_code = ?";
        float discountPercentage = 0.0f;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, discountCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                discountPercentage = rs.getFloat("discount_percentage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discountPercentage;
    }
    
     public List<String> getAllDiscountCodes() {
        List<String> discountList = new ArrayList<>();
        String query = "SELECT discount_code, discount_percentage FROM discounts";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String code = rs.getString("discount_code");
                float percentage = rs.getFloat("discount_percentage");
                discountList.add(code + " (" + percentage + "%)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discountList;
    }
}
