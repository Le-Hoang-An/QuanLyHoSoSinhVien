package controller;

import view.TaiKhoanView;
import view.AdminDashboard;

public class TaiKhoanController {
    private TaiKhoanView view;
    private AdminDashboard dashboard;

    public TaiKhoanController(TaiKhoanView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
