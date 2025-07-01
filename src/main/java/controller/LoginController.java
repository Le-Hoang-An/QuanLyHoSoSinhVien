package controller;

import model.TaiKhoan;
import model.TaiKhoanDAO;
import view.AdminDashboard;
import view.GiangVienView;
import view.KetQuaView;
import view.LoginView;
import view.LopHocView;
import view.MonHocView;
import view.SinhVienView;  // hoặc AdminDashboardView tùy quyền
import view.TaiKhoanView;

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
                AdminDashboard dash = new AdminDashboard();
                dash.setVisible(true);

                // Gắn listener chuyển view
                dash.addSinhVienListener(e -> new SinhVienView().setVisible(true));
                dash.addGiangVienListener(e -> new GiangVienView().setVisible(true));
                dash.addLopHocListener(e -> new LopHocView().setVisible(true));
                dash.addMonHocListener(e -> new MonHocView().setVisible(true));
                dash.addKetQuaListener(e -> new KetQuaView().setVisible(true));
                dash.addTaiKhoanListener(e -> new TaiKhoanView().setVisible(true));
            } else {
                new LopHocView().setVisible(true); // ví dụ quyền user chỉ xem lớp học
            }
        }
    }
}
