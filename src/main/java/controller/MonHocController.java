package controller;

import javax.swing.JOptionPane;
import view.MonHocView;
import view.AdminDashboard;

public class MonHocController {
    private MonHocView view;
    private AdminDashboard dashboard;

    public MonHocController(MonHocView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            System.out.println("Nút Quay lại được nhấn");
            view.setVisible(false);
            if (dashboard != null) {
                dashboard.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi: Không thể quay lại Dashboard!");
            }
        });
    }
}