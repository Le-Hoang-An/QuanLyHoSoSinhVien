package controller;

import view.MonHocView;
import view.AdminDashboard;

public class MonHocController {
    private MonHocView view;
    private AdminDashboard dashboard;

    public MonHocController(MonHocView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
