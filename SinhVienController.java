/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.util.List;
import model.SinhVienDAO;
import model.SinhVien;

/**
 *
 * @author ACER
 */
public class SinhVienController {
    private SinhVienDAO dao;

    public SinhVienController(Connection conn) {
        this.dao= new SinhVienDAO(conn); 
    }

    public List<SinhVien> getDanhSachSinhVien() {
        return dao.getAllSinhVien(); 
    }
    public boolean themMoiSinhVien(SinhVien sv) {
        return dao.addSinhVien(sv);
    }
    
    public boolean suaSinhVien(SinhVien sv) {
        return dao.updateSinhVien(sv);
    }
    
    public boolean xoaSinhVien(String maSv) {
        return dao.deleteSinhVien(maSv);
    }
    
    public List<SinhVien> timKiemSinhVien(String key) {
        return dao.searchSinhVien(key);
    }
}
