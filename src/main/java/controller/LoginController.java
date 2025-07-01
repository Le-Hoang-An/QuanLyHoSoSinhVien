package controller;

import model.TaiKhoan;
import model.TaiKhoanDAO;
import view.AdminDashboard;
import view.LoginView;
import view.LopHocView;

public class LoginController {

    private LoginView loginView;
    private TaiKhoanDAO dao;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.dao = new TaiKhoanDAO();
        this.loginView.addLoginListener(e -> dangNhap());
    }

    private void dangNhap() {
        String user = loginView.getUsername();
        String pass = loginView.getPassword();
        TaiKhoan tk = dao.checkLogin(user, pass);
        if (tk == null) {
            loginView.showMessage("Sai tên đăng nhập hoặc mật khẩu!");
        } else {
            loginView.showMessage("Đăng nhập thành công với quyền: " + tk.getVaiTro());
            loginView.setVisible(false);

            if (tk.getVaiTro().equalsIgnoreCase("admin")) {
                AdminDashboard dash = AdminDashboard.getInstance();
                new AdminDashboardController(dash);
                dash.setVisible(true);
            } else {
                new LopHocView().setVisible(true);
            }
        }
    }
}