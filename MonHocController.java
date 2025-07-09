package controller;

import javax.swing.JOptionPane;
import view.MonHocView;
import view.AdminDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.MonHoc;
import model.MonHocDAO;


public class MonHocController {
    private MonHocView view;
    private AdminDashboard dashboard;
    private MonHocDAO dao;
    

    public MonHocController(MonHocView view, AdminDashboard dashboard, MonHocDAO dao) {
        this.view = view;
        this.dashboard = dashboard;
        this.dao = new MonHocDAO();
        
        loadTable();
        
        this.view.addThemListener(new ThemListener());
        this.view.addSuaListener(new SuaListener());
        this.view.addXoaListener(new XoaListener());
        this.view.addTimListener(new TimListener());
        

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
    private void loadTable(){
        List<MonHoc> danhsach = dao.getAll();
        view.loadTable(danhsach);
        view.hienthi();
    }
    private MonHoc layDuLieuTuView() {
            return new MonHoc(
                view.getMaMon(),
                view.getTenMon(),
                view.getSoTinChi()
        );
    }
    
    class ThemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MonHoc mh = layDuLieuTuView();
            if (mh.getMaMon().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã giảng viên không được để trống.");
                return;
            }

            if (dao.timKiem(mh.getMaMon()).size() > 0) {
                JOptionPane.showMessageDialog(view, "Mã giảng viên đã tồn tại.");
                return;
            }

            dao.them(mh);
            loadTable();
            view.clearForm();
            JOptionPane.showMessageDialog(view, "Đã thêm .");
        }
    }

    class SuaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để sửa.");
                return;
            }

            MonHoc mh = layDuLieuTuView();
            dao.sua(mh);
            loadTable();
            view.clearForm();
            JOptionPane.showMessageDialog(view, "Đã sửa.");
        }
    }
    class XoaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng để xóa.");
                return;
            }

            String maMon = view.getTable().getValueAt(row, 0).toString();
            int xacNhan = JOptionPane.showConfirmDialog(view, "Xóa MonHoc có mã: " + maMon + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                dao.xoa(maMon);
                loadTable();
                view.clearForm();
                JOptionPane.showMessageDialog(view, "Đã xóa .");
            }
        }
    }

    class TimListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tuKhoa = view.getTuKhoaTim().trim();
            if (tuKhoa.isEmpty()) {
                loadTable();
            } else {
                List<MonHoc> ketQua = dao.timKiem(tuKhoa);
                view.loadTable(ketQua);
            }
        }
    }
    
}