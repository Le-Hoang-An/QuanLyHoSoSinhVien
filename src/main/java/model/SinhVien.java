/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class SinhVien {
    private String maSv;
    private String hoTen;
    private String gioiTinh;
    private String ngaySinh;
    private String maLop;
    private String diaChi;

    public SinhVien() {
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

    public String getMaLop() {
        return maLop;
    }

    public String getDiaChi() {
        return diaChi;
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

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public SinhVien(String maSv, String hoTen, String gioiTinh, String ngaySinh, String maLop, String diaChi) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.maLop = maLop;
        this.diaChi = diaChi;
    }
    
}
