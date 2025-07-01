package controller;

import view.LopHocView;
import view.AdminDashboard;

public class LopHocController {
    private LopHocView view;
    private AdminDashboard dashboard;

    public LopHocController(LopHocView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        // Các xử lý khác...

        // Thêm xử lý nút Quay lại
        view.addBackListener(e -> {
            view.dispose();
            dashboard.setVisible(true);
        });
    }
}