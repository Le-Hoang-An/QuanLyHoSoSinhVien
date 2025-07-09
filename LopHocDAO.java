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
public class LopHocDAO {
    private Connection conn;
    public LopHocDAO(Connection conn) {
        this.conn = conn;
    }
    public List<LopHoc> getAllLopHoc(){
        List<LopHoc> list  = new ArrayList<>();
        String sql = "SELECT LH.maLop, LH.tenLop, LH.giangVienCN, LH.coVanHocTap, COUNT(SV.maSv) AS soLuongSV " +
                     "FROM LopHoc LH " +
                     "LEFT JOIN sinh_vien SV ON LH.maLop = SV.maLop " +
                     "GROUP BY LH.maLop, LH.tenLop, LH.giangVienCN, LH.coVanHocTap " +
                     "ORDER BY LH.maLop";
        try {
            Statement stmt = conn .createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
             String maLop = rs.getString("maLop");
             String tenLop = rs.getString("tenLop");
             String giangVienCN = rs.getString("giangVienCN");
             String coVanHocTap = rs.getString("coVanHocTap");
             int soLuongSV = rs.getInt("soLuongSV");
             list.add(new LopHoc(maLop,tenLop,giangVienCN,coVanHocTap,soLuongSV));
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean themLopHoc (LopHoc lh){
        String sql = "Insert into LopHoc(maLop,tenLop,giangVienCN,coVanHocTap) values (?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,lh.getMaLop());
            ps.setString(2, lh.getTenLop());
            ps.setString(3, lh.getGiangVienCN());
            ps.setString(4,lh.getCoVanHocTap());
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean suaLopHoc(LopHoc lh){
        String sql = "Update LopHoc set tenLop = ?,giangVienCN = ?,coVanHocTap = ? where maLop = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lh.getTenLop());
            ps.setString(2, lh.getGiangVienCN());
            ps.setString(3, lh.getCoVanHocTap());
            ps.setString(4, lh.getMaLop());
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean xoaLopHoc(String maLop){
        try{
            
            String sql1= "Delete from sinh_vien where maLop = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1,maLop);
            ps1.executeUpdate();
            String sql2= "Delete from LopHoc where maLop = ?";
            PreparedStatement ps = conn.prepareStatement(sql2);
            ps.setString(1, maLop);
            return ps.executeUpdate() >0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<LopHoc> timkiem(String key){
        List<LopHoc> list = new ArrayList<>();
        String sql = "SELECT LH.maLop, LH.tenLop, LH.giangVienCN, LH.coVanHocTap, COUNT(SV.maSv) AS soLuongSV " +
                     "FROM LopHoc LH " +
                     "LEFT JOIN sinh_vien SV ON LH.maLop = SV.maLop " +
                     "WHERE LH.tenLop LIKE ? " + // Apply search filter
                     "GROUP BY LH.maLop, LH.tenLop, LH.giangVienCN, LH.coVanHocTap " +
                     "ORDER BY LH.maLop";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,"%"+key+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LopHoc lh = new LopHoc(rs.getString("maLop")
                        ,rs.getString("tenLop")
                        ,rs.getString("giangVienCN")
                        ,rs.getString("coVanHocTap")
                        ,rs.getInt("soLuongSV"));
                list.add(lh);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
             System.err.println("SQL Error in timkiem: " + e.getMessage());
        }
        return list;
    }
}
