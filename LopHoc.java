/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class LopHoc {

    private String maLop;
    private String tenLop;
    private String giangVienCN;
    private String coVanHocTap;
    private int soLuongSV;

    public LopHoc(String maLop, String tenLop, String giangVienCN, String coVanHocTap) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.giangVienCN = giangVienCN;
        this.coVanHocTap = coVanHocTap;
        this.soLuongSV = 0; 
    }

    public LopHoc(String maLop, String tenLop, String giangVienCN, String coVanHocTap, int soLuongSV) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.giangVienCN = giangVienCN;
        this.coVanHocTap = coVanHocTap;
        this.soLuongSV = soLuongSV;
    }

    public int getSoLuongSV() {
        return soLuongSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public String getGiangVienCN() {
        return giangVienCN;
    }

    public String getCoVanHocTap() {
        return coVanHocTap;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public void setGiangVienCN(String giangVienCN) {
        this.giangVienCN = giangVienCN;
    }

    public void setCoVanHocTap(String coVanHocTap) {
        this.coVanHocTap = coVanHocTap;
    }

    public void setSoLuongSV(int soLuongSV) {
        this.soLuongSV = soLuongSV;
    }
    
    @Override
    public String toString() {
        return maLop + " - " + tenLop + " - " + giangVienCN + " - "+ coVanHocTap;
    }
}
