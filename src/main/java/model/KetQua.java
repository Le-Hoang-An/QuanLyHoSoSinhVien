/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class KetQua {

    private String maSv;
    private String maMon;
    private float diem;

    public KetQua() {
    }

    public KetQua(String maSv, String maMon, float diem) {
        this.maSv = maSv;
        this.maMon = maMon;
        this.diem = diem;
    }

    public String getMaSv() {
        return maSv;
    }

    public String getMaMon() {
        return maMon;
    }

    public float getDiem() {
        return diem;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
    
}
