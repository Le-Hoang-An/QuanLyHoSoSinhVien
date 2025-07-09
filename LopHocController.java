/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.util.List;
import model.LopHocDAO;
import model.LopHoc;


/**
 *
 * @author ACER
 */
public class LopHocController {
    private LopHocDAO dao;
    public LopHocController(Connection conn) {
        dao = new LopHocDAO(conn);
    }
    public List<LopHoc> getDanhSachLop(){
        return dao.getAllLopHoc();          
    }
    public boolean themLopHoc(LopHoc lh){
        return dao.themLopHoc(lh);
    }
    public boolean suaLopHoc(LopHoc lh){
        return dao.suaLopHoc(lh);
    }
    public boolean xoaLopHoc(String maLop){
        return dao.xoaLopHoc(maLop);
    }
    public List<LopHoc> timkiem(String key){
        return dao.timkiem(key);
    }
}
