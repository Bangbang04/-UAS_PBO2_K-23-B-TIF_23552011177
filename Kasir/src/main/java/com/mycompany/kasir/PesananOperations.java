
package com.mycompany.kasir;

import java.sql.*;
import java.util.*;

public class PesananOperations {
    private Connection conn = DatabaseConnection.getConnection();

    // Buat pesanan baru
    public int buatPesanan(Pesanan pesanan) {
        try {
            String sql = "INSERT INTO pesanan (meja, status) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pesanan.getMeja());
            ps.setString(2, pesanan.getStatus());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int pesananId = rs.getInt(1);
                for (DetailPesanan dp : pesanan.getDetailList()) {
                    String sql2 = "INSERT INTO detail_pesanan (pesanan_id, menu_id, jumlah) VALUES (?,?,?)";
                    PreparedStatement ps2 = conn.prepareStatement(sql2);
                    ps2.setInt(1, pesananId);
                    ps2.setInt(2, dp.getMenu().getId());
                    ps2.setInt(3, dp.getJumlah());
                    ps2.executeUpdate();
                }
                return pesananId;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Ambil semua pesanan
    public List<Pesanan> getSemuaPesanan(List<Menu> menuMaster) {
        List<Pesanan> pesananList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pesanan ORDER BY id DESC";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Pesanan p = new Pesanan(rs.getInt("id"), rs.getString("meja"), rs.getString("status"));
                // Detail
                String sql2 = "SELECT * FROM detail_pesanan WHERE pesanan_id=?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1, p.getId());
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    int menuId = rs2.getInt("menu_id");
                    int jumlah = rs2.getInt("jumlah");
                    Menu menu = menuMaster.stream().filter(m -> m.getId() == menuId).findFirst().orElse(null);
                    if (menu != null) p.tambahDetail(new DetailPesanan(rs2.getInt("id"), menu, jumlah));
                }
                pesananList.add(p);
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return pesananList;
    }

    // Update status pesanan (bayar)
    public boolean updateStatus(int pesananId, String status) {
        try {
            String sql = "UPDATE pesanan SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, pesananId);
            return ps.executeUpdate() > 0;
        } catch(SQLException e) { e.printStackTrace(); }
        return false;
    }
}
