/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class SinhVien {
    private String maSv;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;
    private int soDienThoai;
    private String email;
    private String maLop;
    private String ghiChu;  

    public SinhVien(String maSv, String hoTen, String gioiTinh, String ngaySinh, String diaChi, int soDienThoai, String email, String maLop, String ghiChu) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.maLop = maLop;
        this.ghiChu = ghiChu;
    }

   

    public String getMaSv() {
        return maSv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public int getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDienThoai(int soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    @Override
    public String toString() {
        // Tùy chỉnh định dạng hiển thị trong JList
        return String.format("%s - %s - %s - %s - %s - %d - %s - %s - %s",
                maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu);
    }
}
