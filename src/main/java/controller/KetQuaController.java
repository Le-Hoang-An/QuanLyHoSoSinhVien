package controller;

import model.KetQua;
import model.KetQuaDAO;
import view.KetQuaView;

import javax.swing.*;
import java.awt.event.*;

public class KetQuaController {
    private KetQuaView view;
    private KetQuaDAO dao;

    public KetQuaController(KetQuaView view) {
        this.view = view;
        this.dao = new KetQuaDAO();

        // Nạp dữ liệu ban đầu
        loadTable();

        // Gắn các listener
        view.addThemListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KetQua kq = view.getKetQuaFromForm();
                if (kq == null) {
                    JOptionPane.showMessageDialog(view, "Dữ liệu không hợp lệ!");
                    return;
                }
                if (dao.insert(kq)) {
                    JOptionPane.showMessageDialog(view, "Thêm thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm thất bại!");
                }
            }
        });

        view.addSuaListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                KetQua kq = view.getKetQuaFromForm();
                if (kq == null) {
                    JOptionPane.showMessageDialog(view, "Dữ liệu không hợp lệ!");
                    return;
                }
                if (dao.update(kq)) {
                    JOptionPane.showMessageDialog(view, "Sửa thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Sửa thất bại!");
                }
            }
        });

        view.addXoaListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maSV = view.getMaSV();
                String maMon = view.getMaMon();
                if (dao.delete(maSV, maMon)) {
                    JOptionPane.showMessageDialog(view, "Xóa thành công!");
                    loadTable();
                    view.clearForm();
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa thất bại!");
                }
            }
        });

        view.addLamMoiListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTable();
                view.clearForm();
            }
        });

        view.addTableSelectionListener(e -> {
            view.fillForm();
        });
    }

    public void loadTable() {
        view.setTableData(dao.getAll());
    }
}