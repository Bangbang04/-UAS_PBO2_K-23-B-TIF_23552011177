
package com.mycompany.kasir;

import java.sql.*;
import java.util.*;

public class MenuOperations {
    private Connection conn = DatabaseConnection.getConnection();

    public List<Menu> getAllMenu() {
        List<Menu> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM menu";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                double harga = rs.getDouble("harga");
                String jenis = rs.getString("jenis");
                if (jenis.equalsIgnoreCase("makanan")) {
                    list.add(new Makanan(id, nama, harga));
                } else {
                    list.add(new Minuman(id, nama, harga));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}