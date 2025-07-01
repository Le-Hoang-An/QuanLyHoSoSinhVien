package controller;

import view.KetQuaView;
import view.AdminDashboard;

public class KetQuaController {
    private KetQuaView view;
    private AdminDashboard dashboard;

    public KetQuaController(KetQuaView view, AdminDashboard dashboard) {
        this.view = view;
        this.dashboard = dashboard;

        this.view.addBackListener(e -> {
            view.setVisible(false);
            dashboard.setVisible(true);
        });
    }
}
