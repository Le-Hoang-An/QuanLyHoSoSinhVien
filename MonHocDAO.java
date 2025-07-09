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
public class MonHocDAO {
    
   public List<MonHoc> getAll() {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM monhoc";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MonHoc mh = new MonHoc(
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTinChi")
                );
                list.add(mh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
   
   public void them(MonHoc mh) {
        String sql = "INSERT INTO monhoc(maMon, tenMon, soTinChi) VALUES (?, ?, ?)";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mh.getMaMon());
            ps.setString(2, mh.getTenMon());
            ps.setInt(3, mh.getSoTinChi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
   public void sua(MonHoc mh) {
        String sql = "UPDATE monhoc SET tenMon=?, soTinChi=? WHERE maMon=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mh.getMaMon());
            ps.setString(2, mh.getTenMon());
            ps.setInt(3, mh.getSoTinChi());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoa(String maMon) {
        String sql = "DELETE FROM monhoc WHERE maMon=?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMon);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MonHoc> timKiem(String tuKhoa) {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT * FROM monhoc WHERE maMon LIKE ? OR tenMon LIKE ? ";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String keyword = "%" + tuKhoa + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MonHoc mh = new MonHoc(
                    rs.getString("maMon"),
                    rs.getString("tenMon"),
                    rs.getInt("soTinChi")
                );
                list.add(mh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
