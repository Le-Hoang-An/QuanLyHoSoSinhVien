package model;

import java.sql.*;
import connect.DBConnect;

public class TaiKhoanDAO {
    public TaiKhoan checkLogin(String user, String pass) {
        TaiKhoan tk = null;
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT * FROM taikhoan WHERE tenDangNhap=? AND matKhau=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tk = new TaiKhoan();
                tk.setTenDangNhap(rs.getString("tenDangNhap"));
                tk.setMatKhau(rs.getString("matKhau"));
                tk.setVaiTro(rs.getString("vaiTro"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
