package view;

import controller.GiangVienController;
import model.GiangVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GiangVienView extends JPanel {
    private GiangVienController controller;
    private DefaultTableModel tableModel;
    private JTable tblGiangVien;
    private JTextField txtMaGV, txtHoTen, txtHocVi, txtKhoa, txtSDT, txtEmail, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnClear, btnTimKiem, btnXuatExcel;

    public GiangVienView(Connection conn) {
        controller = new GiangVienController(conn);
        initUI();
        loadDanhSachGiangVien();
        addEvent();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // Panel Form
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setBorder(BorderFactory.createTitledBorder("Thông tin Giảng viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        txtMaGV = new JTextField(20);
        txtHoTen = new JTextField(20);
        txtHocVi = new JTextField(20);
        txtKhoa = new JTextField(20);
        txtSDT = new JTextField(20);
        txtEmail = new JTextField(20);

        String[] labels = {"Mã GV:", "Họ tên:", "Học vị:", "Khoa:", "SĐT:", "Email:"};
        JTextField[] fields = {txtMaGV, txtHoTen, txtHocVi, txtKhoa, txtSDT, txtEmail};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i; gbc.anchor = GridBagConstraints.EAST;
            pnForm.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
            pnForm.add(fields[i], gbc);
        }

        add(pnForm, BorderLayout.NORTH);

        // Table
        String[] columnNames = {"Mã GV", "Họ tên", "Học vị", "Khoa", "SĐT", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblGiangVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblGiangVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Giảng viên"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel Buttons
        JPanel pnButton = new JPanel();
        pnButton.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnClear = new JButton("Clear");
        txtTimKiem = new JTextField(15);
        btnTimKiem = new JButton("Tìm kiếm");
        btnXuatExcel = new JButton("Xuất Excel");

        pnButton.add(txtTimKiem);
        pnButton.add(btnTimKiem);
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.add(btnClear);
        pnButton.add(btnXuatExcel);
        add(pnButton, BorderLayout.SOUTH);
    }

    private void loadDanhSachGiangVien() {
        tableModel.setRowCount(0);
        List<GiangVien> list = controller.getDanhSachGiangVien();
        for (GiangVien gv : list) {
            tableModel.addRow(new Object[]{
                gv.getMaGV(), gv.getHoTen(), gv.getHocVi(),
                gv.getKhoa(), gv.getSoDienThoai(), gv.getEmail()
            });
        }
    }

    private void addEvent() {
        btnThem.addActionListener(e -> {
            GiangVien gv = getInput();
            if (gv == null) return;
            if (controller.themGiangVien(gv)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadDanhSachGiangVien();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        });
        btnSua.addActionListener(e -> {
            GiangVien gv = getInput();
            if (gv == null) return;
            if (controller.suaGiangVien(gv)) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadDanhSachGiangVien();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        });
        btnXoa.addActionListener(e -> {
            String maGV = txtMaGV.getText().trim();
            if (maGV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Chọn giảng viên để xóa");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Xóa " + maGV + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.xoaGiangVien(maGV)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    loadDanhSachGiangVien();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại");
                }
            }
        });
        btnClear.addActionListener(e -> clearForm());
        btnTimKiem.addActionListener(e -> {
            String key = txtTimKiem.getText().trim();
            List<GiangVien> kq = controller.timKiem(key);
            tableModel.setRowCount(0);
            if (kq.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
                loadDanhSachGiangVien();
            } else {
                for (GiangVien gv : kq) {
                    tableModel.addRow(new Object[]{
                        gv.getMaGV(), gv.getHoTen(), gv.getHocVi(),
                        gv.getKhoa(), gv.getSoDienThoai(), gv.getEmail()
                    });
                }
            }
        });
        btnXuatExcel.addActionListener(e -> xuatExcel());
        tblGiangVien.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblGiangVien.getSelectedRow() != -1) {
                int row = tblGiangVien.getSelectedRow();
                txtMaGV.setText(tableModel.getValueAt(row, 0).toString());
                txtHoTen.setText(tableModel.getValueAt(row, 1).toString());
                txtHocVi.setText(tableModel.getValueAt(row, 2).toString());
                txtKhoa.setText(tableModel.getValueAt(row, 3).toString());
                txtSDT.setText(tableModel.getValueAt(row, 4).toString());
                txtEmail.setText(tableModel.getValueAt(row, 5).toString());
            }
        });
    }

    private GiangVien getInput() {
        if (txtMaGV.getText().isEmpty() || txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập đầy đủ thông tin");
            return null;
        }
        return new GiangVien(
            txtMaGV.getText(),
            txtHoTen.getText(),
            txtHocVi.getText(),
            txtKhoa.getText(),
            txtSDT.getText(),
            txtEmail.getText()
        );
    }

    private void clearForm() {
        txtMaGV.setText("");
        txtHoTen.setText("");
        txtHocVi.setText("");
        txtKhoa.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        tblGiangVien.clearSelection();
    }

    private void xuatExcel() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn nơi lưu file Excel");
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (Workbook wb = new XSSFWorkbook()) {
                Sheet sheet = wb.createSheet("GiangVien");
                Row header = sheet.createRow(0);
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    header.createCell(i).setCellValue(tableModel.getColumnName(i));
                }
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        Object value = tableModel.getValueAt(i, j);
                        row.createCell(j).setCellValue(value != null ? value.toString() : "");
                    }
                }
                try (FileOutputStream out = new FileOutputStream(file + ".xlsx")) {
                    wb.write(out);
                }
                JOptionPane.showMessageDialog(this, "Xuất Excel thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + ex.getMessage());
            }
        }
    }
}
