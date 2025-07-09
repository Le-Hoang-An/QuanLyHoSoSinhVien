/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlyhososinhvien;

import connect.DBConnect;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Cursor;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import view.KetQuaView;
import view.LopHocView;
import view.SinhVienView;
import view.TaiKhoanView;
import view.GiangVienView;
import view.MonHocView;
import java.util.List;
import model.GiangVien;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class QuanLyHoSoSinhVien extends JFrame{
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Connection conn;

    public QuanLyHoSoSinhVien() {
        setTitle("Hệ thống quản lý hồ sơ sinh viên");
        setSize(1200, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 247, 250)); // Nền tổng thể sáng

        // ===== Kết nối CSDL =====
        conn = DBConnect.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Không kết nối được CSDL!");
            System.exit(0);
        }

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 10, 10));
        sidebar.setBackground(new Color(44, 62, 80)); // Màu xanh đậm dịu
        sidebar.setBorder(new EmptyBorder(30, 10, 30, 10));

        Font sidebarFont = new Font("Segoe UI", Font.BOLD, 18);
        Color btnBg = new Color(52, 152, 219);
        Color btnFg = Color.WHITE;
        Color btnHover = new Color(41, 128, 185);

        JButton btnLopHoc = new JButton("📋 Quản lý Lớp học");
        JButton btnSinhVien = new JButton("👨‍🎓 Quản lý Sinh viên");
        JButton btnKetQua = new JButton("📊 Quản lý Kết quả");
        JButton btnGiangVien = new JButton("👨‍🏫 Quản lý Giảng viên");
        JButton btnTaiKhoan = new JButton("🔑 Quản lý Tài khoản");
        JButton btnLogout = new JButton("🔙 Đăng xuất");
        JButton[] btns = {btnLopHoc, btnSinhVien, btnKetQua, btnGiangVien, btnTaiKhoan,btnLogout};
        for (JButton btn : btns) {
            btn.setFont(sidebarFont);
            btn.setBackground(btnBg);
            btn.setForeground(btnFg);
            btn.setFocusPainted(false);
            btn.setBorder(new LineBorder(new Color(236, 240, 241), 2, true));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setOpaque(true);
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(btnHover);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(btnBg);
                }
            });
            sidebar.add(btn);
        }
        add(sidebar, BorderLayout.WEST);

        // ===== Main Panel =====
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setOpaque(true);
        mainPanel.setBorder(new LineBorder(new Color(189, 195, 199), 2, true));

        JLabel homeLabel = new JLabel("🏠 Trang chủ hệ thống", SwingConstants.CENTER);
        homeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        homeLabel.setForeground(new Color(41, 128, 185));
        mainPanel.add(homeLabel, "HOME");
        mainPanel.add(new LopHocView(conn), "LOPHOC");
        mainPanel.add(new SinhVienView(conn), "SINHVIEN");
        mainPanel.add(new KetQuaView(), "KETQUA");
        mainPanel.add(new GiangVienView(conn), "GIANGVIEN");
        JLabel taiKhoanLabel = new JLabel("🔑 Quản lý Tài khoản (Đang phát triển)", SwingConstants.CENTER);
        taiKhoanLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        taiKhoanLabel.setForeground(new Color(127, 140, 141));
        mainPanel.add(taiKhoanLabel, "TAIKHOAN");
        add(mainPanel, BorderLayout.CENTER);
        
        // ===== Sự kiện nút =====
        btnLopHoc.addActionListener(e -> cardLayout.show(mainPanel, "LOPHOC"));
        btnSinhVien.addActionListener(e -> cardLayout.show(mainPanel, "SINHVIEN"));
        btnKetQua.addActionListener(e -> cardLayout.show(mainPanel, "KETQUA"));
        btnGiangVien.addActionListener(e -> {
            cardLayout.show(mainPanel, "GIANGVIEN");
        });
        btnTaiKhoan.addActionListener(e -> cardLayout.show(mainPanel, "TAIKHOAN"));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất không ?", "Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
            view.LoginView loginView = new view.LoginView();
            new controller.LoginController(loginView);
            loginView.setVisible(true);
            dispose();}
        });
    }

    public static void main(String[] args) {
        view.LoginView loginView = new view.LoginView();
        new controller.LoginController(loginView);
        loginView.setVisible(true);
    }
}
