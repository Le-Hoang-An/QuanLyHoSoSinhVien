package controller;

import view.LopHocView;
import view.AdminDashboard;

public class LopHocController {
    private LopHocView view;
    private AdminDashboard dashboard;

    public LopHocController(LopHocView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
