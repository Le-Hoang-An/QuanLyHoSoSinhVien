package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private static AdminDashboard instance;
    private JButton btnSinhVien, btnGiangVien, btnLopHoc, btnMonHoc, btnKetQua, btnTaiKhoan;

    private AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        btnSinhVien = new JButton("Quản lý Sinh viên");
        btnGiangVien = new JButton("Quản lý Giảng viên");
        btnLopHoc = new JButton("Quản lý Lớp học");
        btnMonHoc = new JButton("Quản lý Môn học");
        btnKetQua = new JButton("Quản lý Kết quả");
        btnTaiKhoan = new JButton("Quản lý Tài khoản");

        add(btnSinhVien);
        add(btnGiangVien);
        add(btnLopHoc);
        add(btnMonHoc);
        add(btnKetQua);
        add(btnTaiKhoan);
    }

    public static AdminDashboard getInstance() {
        if (instance == null) {
            instance = new AdminDashboard();
        }
        return instance;
    }

    public void addSinhVienListener(ActionListener listener) { btnSinhVien.addActionListener(listener); }
    public void addGiangVienListener(ActionListener listener) { btnGiangVien.addActionListener(listener); }
    public void addLopHocListener(ActionListener listener) { btnLopHoc.addActionListener(listener); }
    public void addMonHocListener(ActionListener listener) { btnMonHoc.addActionListener(listener); }
    public void addKetQuaListener(ActionListener listener) { btnKetQua.addActionListener(listener); }
    public void addTaiKhoanListener(ActionListener listener) { btnTaiKhoan.addActionListener(listener); }
}