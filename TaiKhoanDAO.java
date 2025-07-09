package model;

import connect.DBConnect;
import java.sql.*;
import java.util.*;

public class TaiKhoanDAO {

    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM taikhoan")) {
            while (rs.next()) {
                list.add(new TaiKhoan(
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO taikhoan VALUES(?,?,?)";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getVaiTro());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(TaiKhoan tk) {
        String sql = "UPDATE taikhoan SET matKhau=?, vaiTro=? WHERE tenDangNhap=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getVaiTro());
            ps.setString(3, tk.getTenDangNhap());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String tenDangNhap) {
        String sql = "DELETE FROM taikhoan WHERE tenDangNhap=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDangNhap);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogin(String user, String pass) {
        String sql = "SELECT * FROM taikhoan WHERE tenDangNhap=? AND matKhau=?";
        try (java.sql.Connection conn = connect.DBConnect.getConnection(); java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            java.sql.ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public TaiKhoan checkLoginAndGetAccount(String user, String pass) {
        String sql = "SELECT * FROM taikhoan WHERE tenDangNhap=? AND matKhau=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                    rs.getString("tenDangNhap"),
                    rs.getString("matKhau"),
                    rs.getString("vaiTro")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TaiKhoan> search(String tuKhoa) {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan WHERE tenDangNhap LIKE ? OR vaiTro LIKE ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tuKhoa + "%");
            ps.setString(2, "%" + tuKhoa + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TaiKhoan(
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("vaiTro")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
