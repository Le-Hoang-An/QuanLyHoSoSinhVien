package controller;

import view.GiangVienView;
import view.AdminDashboard;

public class GiangVienController {
    private GiangVienView view;
    private AdminDashboard dashboard;

    public GiangVienController(GiangVienView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
