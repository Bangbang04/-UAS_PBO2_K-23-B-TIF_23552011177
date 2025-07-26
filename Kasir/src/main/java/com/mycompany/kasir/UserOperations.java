
package com.mycompany.kasir;

import java.sql.*;

public class UserOperations {
    private Connection conn = DatabaseConnection.getConnection();

    public boolean registerUser(String username, String password, String role) {
        try {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
            return true;
        } catch(SQLException e) {
            return false;
        }
    }

    public User loginUser(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}