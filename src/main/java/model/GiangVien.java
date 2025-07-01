/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class GiangVien {

    private String maGV;
    private String hoTen;
    private String hocVi;
    private String khoa;
    private String soDienThoai;
    private String email;

    public GiangVien() {
    }

    public GiangVien(String maGV, String hoTen, String hocVi, String khoa, String soDienThoai, String email) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.hocVi = hocVi;
        this.khoa = khoa;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaGV() {
        return maGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getHocVi() {
        return hocVi;
    }

    public String getKhoa() {
        return khoa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
