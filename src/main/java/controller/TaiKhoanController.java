package controller;

import model.TaiKhoan;
import model.TaiKhoanDAO;
import view.TaiKhoanView;

import javax.swing.*;
import java.awt.event.*;

public class TaiKhoanController {
    private TaiKhoanView view;
    private TaiKhoanDAO dao;

    public TaiKhoanController(TaiKhoanView view) {
        this.view = view;
        this.dao = new TaiKhoanDAO();

        loadTable();

        view.addThemListener(e -> {
            TaiKhoan tk = view.getTaiKhoanFromForm();
            if (dao.insert(tk)) {
                JOptionPane.showMessageDialog(view, "Thêm thành công!");
                loadTable();
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Thêm thất bại!");
            }
        });

        view.addSuaListener(e -> {
            TaiKhoan tk = view.getTaiKhoanFromForm();
            if (dao.update(tk)) {
                JOptionPane.showMessageDialog(view, "Sửa thành công!");
                loadTable();
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Sửa thất bại!");
            }
        });

        view.addXoaListener(e -> {
            String tenDangNhap = view.getTenDangNhap();
            if (dao.delete(tenDangNhap)) {
                JOptionPane.showMessageDialog(view, "Xóa thành công!");
                loadTable();
                view.clearForm();
            } else {
                JOptionPane.showMessageDialog(view, "Xóa thất bại!");
            }
        });

        view.addLamMoiListener(e -> {
            loadTable();
            view.clearForm();
        });

        view.addTimKiemListener(e -> {
            String tuKhoa = view.getTuKhoaTimKiem();
            if (tuKhoa.isEmpty()) {
                loadTable();
            } else {
                view.setTableData(dao.search(tuKhoa));
            }
        });

        view.addTableSelectionListener(e -> view.fillForm());
    }

    public void loadTable() {
        view.setTableData(dao.getAll());
    }
}