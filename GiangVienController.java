package controller;

import java.sql.Connection;
import java.util.List;
import model.GiangVien;
import model.GiangVienDAO;

public class GiangVienController {
    private GiangVienDAO dao;

    public GiangVienController(Connection conn) {
        dao = new GiangVienDAO(conn);
    }

    public List<GiangVien> getDanhSachGiangVien() {
        return dao.getAll();
    }

    public boolean themGiangVien(GiangVien gv) {
        return dao.them(gv);
    }

    public boolean suaGiangVien(GiangVien gv) {
        return dao.sua(gv);
    }

    public boolean xoaGiangVien(String maGV) {
        return dao.xoa(maGV);
    }

    public List<GiangVien> timKiem(String keyword) {
        return dao.timKiem(keyword);
    }
}
