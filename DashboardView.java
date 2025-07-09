package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private JButton btnTaiKhoan, btnKetQua, btnSinhVien, btnLopHoc, btnMonHoc, btnGiangVien, btnThoat;

    public DashboardView() {
        setTitle("Quản Lý Học Sinh Sinh Viên - Dashboard");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("DASHBOARD", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        btnTaiKhoan = new JButton("Quản lý Tài khoản");
        btnKetQua = new JButton("Quản lý Kết quả");
        btnSinhVien = new JButton("Quản lý Sinh viên");
        btnLopHoc = new JButton("Quản lý Lớp học");
        btnMonHoc = new JButton("Quản lý Môn học");
        btnGiangVien = new JButton("Quản lý Giảng viên");
        btnThoat = new JButton("Thoát");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panel.add(btnTaiKhoan);
        panel.add(btnKetQua);
        panel.add(btnSinhVien);
        panel.add(btnLopHoc);
        panel.add(btnMonHoc);
        panel.add(btnGiangVien);
        panel.add(btnThoat);

        setLayout(new BorderLayout());
        add(lblTitle, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    // Đăng ký sự kiện cho các nút
    public void addTaiKhoanListener(ActionListener listener) {
        btnTaiKhoan.addActionListener(listener);
    }

    public void addKetQuaListener(ActionListener listener) {
        btnKetQua.addActionListener(listener);
    }

    public void addSinhVienListener(ActionListener listener) {
        btnSinhVien.addActionListener(listener);
    }

    public void addLopHocListener(ActionListener listener) {
        btnLopHoc.addActionListener(listener);
    }

    public void addMonHocListener(ActionListener listener) {
        btnMonHoc.addActionListener(listener);
    }

    public void addGiangVienListener(ActionListener listener) {
        btnGiangVien.addActionListener(listener);
    }

    public void addThoatListener(ActionListener listener) {
        btnThoat.addActionListener(listener);
    }
}