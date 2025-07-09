package controller;

import model.TaiKhoanDAO;
import view.LoginView;
import view.DashboardView;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private TaiKhoanDAO dao;

    public LoginController(LoginView view) {
        this.view = view;
        this.dao = new TaiKhoanDAO();

        view.addLoginListener(e -> {
            String user = view.getUsername();
            String pass = view.getPassword();
            if (dao.checkLogin(user, pass)) {
                view.showMessage("Đăng nhập thành công!");
                view.dispose();
                DashboardView dash = new DashboardView();
                new DashboardController(dash); // Gắn controller cho Dashboard
                dash.setVisible(true);
            } else {
                view.showMessage("Sai tài khoản hoặc mật khẩu!");
                view.clearFields();
            }
        });

        view.addThoatListener(e -> System.exit(0));
    }
}