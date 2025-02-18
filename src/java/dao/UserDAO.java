/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;
import java.sql.*;
/**
 *
 * @author Aruna
 */
public class UserDAO {

    // Validate User Credentials
    public User validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error validating user: " + e.getMessage());
        }
        return user;
    }
}
