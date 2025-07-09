package model;

import connect.DBConnect;
import java.sql.*;
import java.util.*;

public class KetQuaDAO {

    public List<KetQua> getAll() {
        List<KetQua> list = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM ketqua")) {
            while (rs.next()) {
                list.add(new KetQua(
                    rs.getString("maSV"),
                    rs.getString("maMon"),
                    rs.getFloat("diem")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(KetQua kq) {
        String sql = "INSERT INTO ketqua VALUES(?,?,?)";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kq.getMaSv());
            ps.setString(2, kq.getMaMon());
            ps.setFloat(3, kq.getDiem());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(KetQua kq) {
        String sql = "UPDATE ketqua SET diem=? WHERE maSV=? AND maMon=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, kq.getDiem());
            ps.setString(2, kq.getMaSv());
            ps.setString(3, kq.getMaMon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(String maSV, String maMon) {
        String sql = "DELETE FROM ketqua WHERE maSV=? AND maMon=?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ps.setString(2, maMon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}