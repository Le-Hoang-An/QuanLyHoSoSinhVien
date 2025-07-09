/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.SinhVienController;
import controller.LopHocController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.SinhVien;
import model.LopHoc;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class SinhVienView extends JPanel {
    private SinhVienController controller;
    private LopHocController lopHocController; 
    private DefaultTableModel tableModel;
    private JTable tblSinhVien;
    
    // Các trường nhập liệu
    private JTextField txtMaSv, txtHoTen, txtGioiTinh, txtNgaySinh, txtDiaChi, txtSoDienThoai, txtEmail, txtGhiChu, txtTimKiem;
    private JComboBox<String> cbMaLop;
    
    // Các nút chức năng
    private JButton btnThem, btnSua, btnXoa, btnClear, btnTimKiem;

    public SinhVienView(Connection conn) {
        try {
            // Khởi tạo SinhVienController
            controller = new SinhVienController(conn);
            // Khởi tạo LopHocController
            lopHocController = new LopHocController(conn);
        } catch (Exception e) {
            // Log lỗi và hiển thị thông báo cho người dùng
            Logger.getLogger(SinhVienView.class.getName()).log(Level.SEVERE, "Lỗi khởi tạo controller: ", e);
            JOptionPane.showMessageDialog(this, "Không thể kết nối cơ sở dữ liệu hoặc khởi tạo controller. Vui lòng kiểm tra lại kết nối.", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
            // Vô hiệu hóa các chức năng nếu controller không được khởi tạo
            disableUI();
            return; // Dừng khởi tạo UI nếu có lỗi
        }
        
        // Kiểm tra lại các controller sau khi khởi tạo
        if (controller == null || lopHocController == null) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình khởi tạo các chức năng quản lý. Vui lòng thử lại.", "Lỗi khởi tạo", JOptionPane.ERROR_MESSAGE);
            disableUI();
            return;
        }
        
        initialUI();
        loadDanhSachSinhVien();
        addEvent();
    }

    public void initialUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ==== Panel Form Nhập liệu ====
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setBorder(BorderFactory.createTitledBorder("Thông tin Sinh viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Hàng 0
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Mã SV:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; txtMaSv = new JTextField(20); pnForm.add(txtMaSv, gbc);
        gbc.gridx = 2; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Họ Tên:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; txtHoTen = new JTextField(20); pnForm.add(txtHoTen, gbc);

        // Hàng 1
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; txtGioiTinh = new JTextField(20); pnForm.add(txtGioiTinh, gbc);
        gbc.gridx = 2; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; txtNgaySinh = new JTextField(20); pnForm.add(txtNgaySinh, gbc);

        // Hàng 2
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; txtDiaChi = new JTextField(20); pnForm.add(txtDiaChi, gbc);
        gbc.gridx = 2; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("SĐT:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; txtSoDienThoai = new JTextField(20); pnForm.add(txtSoDienThoai, gbc);
        
        // Hàng 3
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; txtEmail = new JTextField(20); pnForm.add(txtEmail, gbc);
        gbc.gridx = 2; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Mã lớp:"), gbc);
        gbc.gridx = 3; gbc.anchor = GridBagConstraints.WEST; 
        cbMaLop = new JComboBox<>(); 
        loadMaLopIntoComboBox(); // Tải dữ liệu mã lớp vào JComboBox
        pnForm.add(cbMaLop, gbc);

        // Hàng 4
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST; pnForm.add(new JLabel("Ghi chú:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.WEST; txtGhiChu = new JTextField(20); pnForm.add(txtGhiChu, gbc);
        gbc.gridwidth = 1;

        add(pnForm, BorderLayout.NORTH);

        // ==== Panel Danh sách Sinh viên (JTable) ====
        String[] columnNames = {"Mã SV", "Họ Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "SĐT", "Email", "Mã lớp", "Ghi chú"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSinhVien = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblSinhVien);
        add(scrollPane, BorderLayout.CENTER);

        // ==== Panel Buttons và Tìm kiếm ====
        JPanel pnControl = new JPanel(new BorderLayout());
        
        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnClear = new JButton("Clear");
        pnButtons.add(btnThem);
        pnButtons.add(btnSua);
        pnButtons.add(btnXoa);
        pnButtons.add(btnClear);
        pnControl.add(pnButtons, BorderLayout.CENTER);

        JPanel pnSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        txtTimKiem = new JTextField(20);
        txtTimKiem.setText("Tìm kiếm theo tên SV...");
        btnTimKiem = new JButton("Tìm kiếm");
        pnSearch.add(txtTimKiem);
        pnSearch.add(btnTimKiem);
        pnControl.add(pnSearch, BorderLayout.EAST);

        add(pnControl, BorderLayout.SOUTH);
    }

    private void loadDanhSachSinhVien() {
        if (controller == null) { // Kiểm tra controller
            tableModel.setRowCount(0); // Xóa bảng
            return; // Không tải dữ liệu nếu controller null
        }
        tableModel.setRowCount(0);
        List<SinhVien> sinhVienList = controller.getDanhSachSinhVien();
        for (SinhVien sv : sinhVienList) {
            Object[] rowData = {
                sv.getMaSv(),
                sv.getHoTen(),
                sv.getGioiTinh(),
                sv.getNgaySinh(),
                sv.getDiaChi(),
                sv.getSoDienThoai(),
                sv.getEmail(),
                sv.getMaLop(),
                sv.getGhiChu()
            };
            tableModel.addRow(rowData);
        }
    }

    private void loadMaLopIntoComboBox() {
        DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<>();
        if (lopHocController == null) { // Kiểm tra lopHocController
            cbMaLop.setModel(cbModel); // Thiết lập model trống
            return; // Không tải dữ liệu nếu controller null
        }
        
        List<LopHoc> danhSachLop = lopHocController.getDanhSachLop(); 
        for (LopHoc lh : danhSachLop) {
            cbModel.addElement(lh.getMaLop() + " - " + lh.getTenLop());
        }
        cbMaLop.setModel(cbModel);
    }

    private void addEvent() {
        if (controller == null || lopHocController == null) { // Không thêm sự kiện nếu controller null
            return;
        }

        btnThem.addActionListener(e -> {
            String maSv = txtMaSv.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            String gioiTinh = txtGioiTinh.getText().trim();
            String ngaySinh = txtNgaySinh.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String soDienThoaiStr = txtSoDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            String maLopFull = (String) cbMaLop.getSelectedItem();
            String maLop = "";
            if (maLopFull != null && !maLopFull.isEmpty()) {
                maLop = maLopFull.split(" - ")[0];
            }
            String ghiChu = txtGhiChu.getText().trim();

            if (maSv.isEmpty() || hoTen.isEmpty() || ngaySinh.isEmpty() || maLop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã SV, Họ tên, Ngày sinh và chọn Mã lớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int soDienThoai;
            try {
                soDienThoai = Integer.parseInt(soDienThoaiStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SinhVien sv = new SinhVien(maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu);
            if (controller.themMoiSinhVien(sv)) {
                JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công!");
                loadDanhSachSinhVien();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại! Có thể Mã SV đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnSua.addActionListener(e -> {
            String maSv = txtMaSv.getText().trim();
            if (maSv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần sửa từ bảng hoặc nhập Mã SV.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hoTen = txtHoTen.getText().trim();
            String gioiTinh = txtGioiTinh.getText().trim();
            String ngaySinh = txtNgaySinh.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String soDienThoaiStr = txtSoDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            String maLopFull = (String) cbMaLop.getSelectedItem();
            String maLop = "";
            if (maLopFull != null && !maLopFull.isEmpty()) {
                maLop = maLopFull.split(" - ")[0];
            }
            String ghiChu = txtGhiChu.getText().trim();

            int soDienThoai;
            try {
                soDienThoai = Integer.parseInt(soDienThoaiStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SinhVien sv = new SinhVien(maSv, hoTen, gioiTinh, ngaySinh, diaChi, soDienThoai, email, maLop, ghiChu);
            if (controller.suaSinhVien(sv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công!");
                loadDanhSachSinhVien();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnXoa.addActionListener(e -> {
            String maSv = txtMaSv.getText().trim();
            if (maSv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần xóa từ bảng hoặc nhập Mã SV.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên có Mã SV: " + maSv + " không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (controller.xoaSinhVien(maSv)) {
                    JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công!");
                    loadDanhSachSinhVien();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sinh viên thất bại! Có thể sinh viên không tồn tại hoặc có ràng buộc dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnClear.addActionListener(e -> clearForm());

        btnTimKiem.addActionListener(e -> {
            String key = txtTimKiem.getText().trim();
            
            if (key.isEmpty() || key.equals("Tìm kiếm theo tên SV...")) {
                loadDanhSachSinhVien();
                JOptionPane.showMessageDialog(this, "Hiển thị lại toàn bộ danh sách sinh viên.");
                txtTimKiem.setText("Tìm kiếm theo tên SV...");
                return;
            }

            List<SinhVien> kq = controller.timKiemSinhVien(key);
            tableModel.setRowCount(0);

            if (kq.isEmpty()) {
                loadDanhSachSinhVien();
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên nào có tên: \"" + key + "\". Đang hiển thị lại toàn bộ danh sách.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (SinhVien sv : kq) {
                    Object[] rowData = {
                        sv.getMaSv(), sv.getHoTen(), sv.getGioiTinh(), sv.getNgaySinh(),
                        sv.getDiaChi(), sv.getSoDienThoai(), sv.getEmail(), sv.getMaLop(), sv.getGhiChu()
                    };
                    tableModel.addRow(rowData);
                }
            }
        });
        
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().equals("Tìm kiếm theo tên SV...")) {
                    txtTimKiem.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtTimKiem.getText().isEmpty()) {
                    txtTimKiem.setText("Tìm kiếm theo tên SV...");
                }
            }
        });

        tblSinhVien.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblSinhVien.getSelectedRow();
                if (selectedRow != -1) {
                    txtMaSv.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtHoTen.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtGioiTinh.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtNgaySinh.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtDiaChi.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    txtSoDienThoai.setText(tableModel.getValueAt(selectedRow, 5).toString());
                    txtEmail.setText(tableModel.getValueAt(selectedRow, 6).toString());
                    String maLopSelected = tableModel.getValueAt(selectedRow, 7).toString();
                    
                    for (int i = 0; i < cbMaLop.getItemCount(); i++) {
                        if (cbMaLop.getItemAt(i).startsWith(maLopSelected + " -")) {
                            cbMaLop.setSelectedIndex(i);
                            break;
                        }
                    }
                    
                    txtGhiChu.setText(tableModel.getValueAt(selectedRow, 8).toString());
                } else {
                    clearForm();
                }
            }
        });
    }

    private void clearForm() {
        txtMaSv.setText("");
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        cbMaLop.setSelectedIndex(-1);
        txtGhiChu.setText("");
        txtTimKiem.setText("Tìm kiếm theo tên SV...");
        tblSinhVien.clearSelection();
    }
    
    // Phương thức để vô hiệu hóa các thành phần UI khi có lỗi
    private void disableUI() {
        // Vô hiệu hóa các nút
        if (btnThem != null) btnThem.setEnabled(false);
        if (btnSua != null) btnSua.setEnabled(false);
        if (btnXoa != null) btnXoa.setEnabled(false);
        if (btnClear != null) btnClear.setEnabled(false);
        if (btnTimKiem != null) btnTimKiem.setEnabled(false);

        // Vô hiệu hóa các trường nhập liệu
        if (txtMaSv != null) txtMaSv.setEditable(false);
        if (txtHoTen != null) txtHoTen.setEditable(false);
        if (txtGioiTinh != null) txtGioiTinh.setEditable(false);
        if (txtNgaySinh != null) txtNgaySinh.setEditable(false);
        if (txtDiaChi != null) txtDiaChi.setEditable(false);
        if (txtSoDienThoai != null) txtSoDienThoai.setEditable(false);
        if (txtEmail != null) txtEmail.setEditable(false);
        if (txtGhiChu != null) txtGhiChu.setEditable(false);
        if (txtTimKiem != null) txtTimKiem.setEditable(false);
        if (cbMaLop != null) cbMaLop.setEnabled(false);
        
        // Vô hiệu hóa bảng
        if (tblSinhVien != null) tblSinhVien.setEnabled(false);
    }
}