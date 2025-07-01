package controller;

import view.SinhVienView;
import view.AdminDashboard;

public class SinhVienController {
    private SinhVienView view;
    private AdminDashboard dashboard;

    public SinhVienController(SinhVienView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
