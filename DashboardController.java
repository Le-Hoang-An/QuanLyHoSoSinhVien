package controller;

import view.DashboardView;
import view.TaiKhoanView;
import view.KetQuaView;

public class DashboardController {
    private DashboardView view;

    public DashboardController(DashboardView view) {
        this.view = view;

        view.addTaiKhoanListener(e -> {
            TaiKhoanView v = new TaiKhoanView();
            new TaiKhoanController(v);
            v.setVisible(true);
        });
        view.addKetQuaListener(e -> {
            KetQuaView v = new KetQuaView();
            new KetQuaController(v);
            v.setVisible(true);
        });
        // Thêm các chức năng khác nếu cần
        view.addThoatListener(e -> System.exit(0));
    }
}