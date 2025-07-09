package controller;

import model.TaiKhoanDAO;
import view.LoginView;
import view.DashboardView;

import javax.swing.*;
import model.TaiKhoan;

public class LoginController {
    private LoginView view;
    private TaiKhoanDAO dao;

    public LoginController(LoginView view) {
        this.view = view;
        this.dao = new TaiKhoanDAO();

        view.addLoginListener(e -> {
            String user = view.getUsername();
            String pass = view.getPassword();
            TaiKhoan tk = dao.checkLoginAndGetAccount(user, pass);
            if (tk != null) {
                view.showMessage("Đăng nhập thành công!");
                view.dispose();
                if ("admin".equalsIgnoreCase(tk.getVaiTro())) {
                   new com.mycompany.quanlyhososinhvien.QuanLyHoSoSinhVien().setVisible(true);
                } else {
                    DashboardView dash = new DashboardView();
                    new DashboardController(dash); 
                    dash.setVisible(true);
                }
            } else {
                view.showMessage("Sai tài khoản hoặc mật khẩu!");
                view.clearFields();
            }
        });

        view.addThoatListener(e -> System.exit(0));
    }
}