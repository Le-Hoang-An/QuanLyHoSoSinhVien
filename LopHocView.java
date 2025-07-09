package view;

import controller.LopHocController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.LopHoc;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
// Apache POI import để xuất Excel
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class LopHocView extends JPanel {
    private LopHocController controller;
//  private JList<LopHoc> lstLop;
//  private DefaultListModel<LopHoc> listModel;
    private DefaultTableModel tableModel;
    private JTable tblLopHoc;
     private JTextField txtMaLop, txtTenLop, txtGiangVien, txtCoVan,txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnClear,btnTimKiem,btnXuatExcel;

    public LopHocView(Connection conn) {
        controller = new LopHocController(conn);
        InitialUI();
        loadDanhSachLop();
        addEvent();
    }

    public void InitialUI() {
        setLayout(new BorderLayout(10, 10));

        // ==== Panel Form ====
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setBorder(BorderFactory.createTitledBorder("Thông tin Lớp học")); // Thêm đường viền
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // khoảng cách giữa các thành phần

        JLabel lblMaLop = new JLabel("Mã lớp:");
        JLabel lblTenLop = new JLabel("Tên lớp:");
        JLabel lblGiangVien = new JLabel("Giảng viên CN:");
        JLabel lblCoVan = new JLabel("Cố vấn học tập:");

        txtMaLop = new JTextField(20);
        txtTenLop = new JTextField(20);
        txtGiangVien = new JTextField(20);
        txtCoVan = new JTextField(20);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        pnForm.add(lblMaLop, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        pnForm.add(txtMaLop, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        pnForm.add(lblTenLop, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        pnForm.add(txtTenLop, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        pnForm.add(lblGiangVien, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        pnForm.add(txtGiangVien, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        pnForm.add(lblCoVan, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        pnForm.add(txtCoVan, gbc);

        add(pnForm, BorderLayout.NORTH);

        // ==== Panel Danh sách lớp ====
//        listModel = new DefaultListModel<>();
//        lstLop = new JList<>(listModel);
//        JScrollPane scrollPane = new JScrollPane(lstLop);

        String[] columnNames = {"Mã lớp","Tên lớp","Chủ nhiệm","Cố vấn", "Số lượng SV"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLopHoc = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblLopHoc);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách Lớp học")); // Thêm đường viền
        add(scrollPane, BorderLayout.CENTER);

        // ==== Panel Buttons ====
        JPanel pnButton = new JPanel();
        pnButton.setBorder(BorderFactory.createTitledBorder("Chức năng")); // Thêm đường viền
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnClear = new JButton("Clear");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tim kiem");
        btnXuatExcel = new JButton("Xuat excel");
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(txtTimKiem);
        searchPanel.add(btnTimKiem);
        pnButton.add(searchPanel);  
        pnButton.add(btnThem);
        pnButton.add(btnSua);
        pnButton.add(btnXoa);
        pnButton.add(btnClear);
        pnButton.add(btnXuatExcel);
        add(pnButton, BorderLayout.SOUTH);
    }

    private void loadDanhSachLop() {
        tableModel.setRowCount(0);
        List<LopHoc> lophoc = controller.getDanhSachLop();
        for(LopHoc lh : lophoc){
            Object[] rowData = {
                lh.getMaLop(),
                lh.getTenLop(),
                lh.getGiangVienCN(),
                lh.getCoVanHocTap(),
                lh.getSoLuongSV()
            };
            tableModel.addRow(rowData);
        }
    }

    private void addEvent() {
        btnThem.addActionListener(e -> {
            LopHoc lh = new LopHoc(
                txtMaLop.getText(),
                txtTenLop.getText(),
                txtGiangVien.getText(),
                txtCoVan.getText()
            );
            if(txtMaLop.getText().trim().isEmpty() || 
               txtTenLop.getText().trim().isEmpty()||
               txtGiangVien.getText().trim().isEmpty()||
               txtCoVan.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Nhap thieu thong tin");
                return;
            }
            if (controller.themLopHoc(lh)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadDanhSachLop();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        });
        btnSua.addActionListener(e->{
            LopHoc lh = new LopHoc(txtMaLop.getText(), txtTenLop.getText(), txtGiangVien.getText(), txtCoVan.getText());
            if(controller.suaLopHoc(lh)){
                JOptionPane.showMessageDialog(this, "Sua thanh cong");
                loadDanhSachLop();
                clearForm();
            }else{
                JOptionPane.showMessageDialog(this, "Sua that bai");
            }
        });
        btnXoa.addActionListener(e->{
            String maLop = txtMaLop.getText();
            if(maLop.isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp để xóa");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa "+maLop +" không ?","Xác nhận xóa",JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
            if(controller.xoaLopHoc(maLop)){
                JOptionPane.showMessageDialog(this, "Xoa thanh cong");
                loadDanhSachLop();
                clearForm();
            }else{
                JOptionPane.showMessageDialog(this, "Xoa that bai");
            }
            }
        });
        btnClear.addActionListener(e -> clearForm());
        btnTimKiem.addActionListener(e->{
            String key = txtTimKiem.getText().trim();
            List<LopHoc> kq = controller.timkiem(key);
            tableModel.setRowCount(0);
            if(kq.isEmpty()){
                loadDanhSachLop(); 
                JOptionPane.showMessageDialog(this, "Khong tim thay");
                return;
            }else{
                for(LopHoc lh: kq){
                    Object[] rowData= {
                        lh.getMaLop(),
                        lh.getTenLop(),
                        lh.getGiangVienCN(),
                        lh.getCoVanHocTap(),
                        lh.getSoLuongSV() // Thêm số lượng sinh viên vào rowData
                    };
                    tableModel.addRow(rowData);
                }
            }
        });
        btnXuatExcel.addActionListener(e->exportToExcel());
        tblLopHoc.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblLopHoc.getSelectedRow();
                if (selectedRow != -1) {
                    String maLop = (String) tableModel.getValueAt(selectedRow, 0);
                    String tenLop = (String) tableModel.getValueAt(selectedRow, 1);
                    String giangVienCN = (String) tableModel.getValueAt(selectedRow, 2);
                    String coVanHocTap = (String) tableModel.getValueAt(selectedRow, 3);
                    txtMaLop.setText(maLop);
                    txtTenLop.setText(tenLop);
                    txtGiangVien.setText(giangVienCN);
                    txtCoVan.setText(coVanHocTap);
                } else {
                    clearForm();
                }
            }
        });
        
//        lstLop.addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting() && lstLop.getSelectedIndex() != -1) {
//                LopHoc selected = lstLop.getSelectedValue();
//                txtMaLop.setText(selected.getMaLop());
//                txtTenLop.setText(selected.getTenLop());
//                txtGiangVien.setText(selected.getGiangVienCN());
//                txtCoVan.setText(selected.getCoVanHocTap());
//            }
//        });
        
    }
    
    private void clearForm() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtGiangVien.setText("");
        txtCoVan.setText("");
        tblLopHoc.clearSelection();
//        lstLop.clearSelection();
    }
    private void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx"));
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.toString() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("DanhSachLopHoc");

                // Header
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < tblLopHoc.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(tblLopHoc.getColumnName(i));
                }

                // Data
                for (int i = 0; i < tblLopHoc.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tblLopHoc.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        Object value = tblLopHoc.getValueAt(i, j);
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }

                FileOutputStream out = new FileOutputStream(fileToSave);
                workbook.write(out);
                out.close();

                JOptionPane.showMessageDialog(this, "Xuất Excel thành công:\n" + fileToSave);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + ex.getMessage());
            }
        }
    }
}