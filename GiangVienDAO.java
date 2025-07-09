/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import connect.DBConnect;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class GiangVienDAO {
    private Connection conn;
    public GiangVienDAO(Connection conn) {
        this.conn = conn;
    }
    public List<GiangVien> getAll() {
        List<GiangVien> list = new ArrayList<>();
        String sql = "SELECT * FROM giangvien";
        try (
           PreparedStatement ps = conn.prepareStatement(sql); 
           ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GiangVien gv = new GiangVien(
                    rs.getString("maGV"),
                    rs.getString("hoTen"),
                    rs.getString("hocVi"),
                    rs.getString("khoa"),
                    rs.getString("soDienThoai"),
                    rs.getString("email")
                );
                list.add(gv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean them(GiangVien gv) {
        String sql = "INSERT INTO giangvien(maGV, hoTen, hocVi, khoa, soDienThoai, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, gv.getMaGV());
            ps.setString(2, gv.getHoTen());
            ps.setString(3, gv.getHocVi());
            ps.setString(4, gv.getKhoa());
            ps.setString(5, gv.getSoDienThoai());
            ps.setString(6, gv.getEmail());
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sua(GiangVien gv) {
        String sql = "UPDATE giangvien SET hoTen=?, hocVi=?, khoa=?, soDienThoai=?, email=? WHERE maGV=?";
        try (
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, gv.getHoTen());
            ps.setString(2, gv.getHocVi());
            ps.setString(3, gv.getKhoa());
            ps.setString(4, gv.getSoDienThoai());
            ps.setString(5, gv.getEmail());
            ps.setString(6, gv.getMaGV());
           return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoa(String maGV) {
        String sql = "DELETE FROM giangvien WHERE maGV=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maGV);
            return ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<GiangVien> timKiem(String tuKhoa) {
        List<GiangVien> list = new ArrayList<>();
        String sql = "SELECT * FROM giangvien WHERE maGV LIKE ? OR hoTen LIKE ? ";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String keyword = "%" + tuKhoa + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GiangVien gv = new GiangVien(
                        rs.getString("maGV"),
                        rs.getString("hoTen"),
                        rs.getString("hocVi"),
                        rs.getString("khoa"),
                        rs.getString("soDienThoai"),
                        rs.getString("email")
                    );
                    list.add(gv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
