package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;
import controller.*;

public class AdminDashboardController {

    private AdminDashboard dashboard;

    public AdminDashboardController(AdminDashboard dashboard) {
        this.dashboard = dashboard;

        // Xử lý nút mở Sinh viên
        this.dashboard.addSinhVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinhVienView view = new SinhVienView();
                new SinhVienController(view, dashboard);
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });

        // Xử lý nút mở Giảng viên
        this.dashboard.addGiangVienListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GiangVienView view = new GiangVienView();
                new GiangVienController(view, dashboard);
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });

        // Xử lý nút mở Lớp học
        this.dashboard.addLopHocListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LopHocView view = new LopHocView();
                new LopHocController(view, dashboard); // phải truyền dashboard vào!
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });

        // Xử lý nút mở Môn học
        this.dashboard.addMonHocListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MonHocView view = new MonHocView();
                new MonHocController(view, dashboard);
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });

        // Xử lý nút mở Kết quả
        this.dashboard.addKetQuaListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KetQuaView view = new KetQuaView();
                new KetQuaController(view, dashboard);
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });

        // Xử lý nút mở Tài khoản
        this.dashboard.addTaiKhoanListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKhoanView view = new TaiKhoanView();
                new TaiKhoanController(view, dashboard);
                view.setVisible(true);
                dashboard.setVisible(false);
            }
        });
    }
}
