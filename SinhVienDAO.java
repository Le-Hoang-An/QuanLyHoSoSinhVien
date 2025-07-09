/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class SinhVienDAO {
    private Connection conn;
    public SinhVienDAO(Connection conn){
        this.conn = conn;
    }
    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> list = new ArrayList<>();
        String sql = "Select * from sinh_vien";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maSv = rs.getString("maSv");
                String hoTen = rs.getString("hoTen");
                String gioiTinh = rs.getString("gioiTinh");
                String ngaySinh = rs.getString("ngaySinh");
                String diaChi = rs.getString("diaChi");
                int soDienThoai = rs.getInt("soDienThoai");
                String email = rs.getString("email");
                String maLop = rs.getString("maLop");
                String ghiChu = rs.getString("ghiChu");
                list.add(new SinhVien(maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu));     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     // Phương thức thêm mới Sinh viên
    public boolean addSinhVien(SinhVien sv) {
        String sql = "INSERT INTO sinh_vien (maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getMaSv());
            ps.setString(2, sv.getHoTen());
            ps.setString(3, sv.getGioiTinh());
            ps.setString(4,  sv.getNgaySinh()); 
            ps.setString(5, sv.getDiaChi());
            ps.setInt(6, sv.getSoDienThoai());
            ps.setString(7, sv.getEmail());
            ps.setString(8, sv.getMaLop());
            ps.setString(9, sv.getGhiChu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // e.printStackTrace(); // In ra lỗi để debug
            System.err.println("Lỗi khi thêm sinh viên: " + e.getMessage());
            return false;
        }
    }
    public boolean updateSinhVien(SinhVien sv) {
        String sql = "UPDATE sinh_vien SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, email = ?, maLop = ?, ghiChu = ? WHERE maSv = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sv.getHoTen());
            ps.setString(2, sv.getGioiTinh());
            ps.setString(3, sv.getNgaySinh());
            ps.setString(4, sv.getDiaChi());
            ps.setInt(5, sv.getSoDienThoai());
            ps.setString(6, sv.getEmail());
            ps.setString(7, sv.getMaLop());
            ps.setString(8, sv.getGhiChu());
            ps.setString(9, sv.getMaSv()); // Điều kiện WHERE
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("Lỗi khi cập nhật sinh viên: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteSinhVien(String maSv) {
        String sql = "DELETE FROM sinh_vien WHERE maSv = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSv);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("Lỗi khi xóa sinh viên: " + e.getMessage());
            return false;
        }
    }
    public List<SinhVien> searchSinhVien(String key) {
        List<SinhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM sinh_vien WHERE hoTen LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%"); // Tìm kiếm gần đúng
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSv = rs.getString("maSv");
                    String hoTen = rs.getString("hoTen");
                    String gioiTinh = rs.getString("gioiTinh");
                    String ngaySinh = rs.getString("ngaySinh");
                    String diaChi = rs.getString("diaChi");
                    int soDienThoai = rs.getInt("soDienThoai");
                    String email = rs.getString("email");
                    String maLop = rs.getString("maLop");
                    String ghiChu = rs.getString("ghiChu");
                    list.add(new SinhVien(maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
