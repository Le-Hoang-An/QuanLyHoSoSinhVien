package view;

import model.TaiKhoan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TaiKhoanView extends JFrame {
    private JTextField txtTenDangNhap, txtMatKhau, txtVaiTro, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable table;
    private DefaultTableModel model;

    public TaiKhoanView() {
        setTitle("Quản lý tài khoản");
        setSize(750, 450);
        setLocationRelativeTo(null);

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("Tên đăng nhập:"));
        txtTenDangNhap = new JTextField();
        inputPanel.add(txtTenDangNhap);

        inputPanel.add(new JLabel("Mật khẩu:"));
        txtMatKhau = new JTextField();
        inputPanel.add(txtMatKhau);

        inputPanel.add(new JLabel("Vai trò:"));
        txtVaiTro = new JTextField();
        inputPanel.add(txtVaiTro);

        inputPanel.add(new JLabel("Tìm kiếm:"));
        txtTimKiem = new JTextField();
        inputPanel.add(txtTimKiem);

        // Panel nút chức năng
        JPanel btnPanel = new JPanel();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");
        btnTimKiem = new JButton("Tìm kiếm");
        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);
        btnPanel.add(btnTimKiem);

        // Bảng
        model = new DefaultTableModel(new String[]{"Tên đăng nhập", "Mật khẩu", "Vai trò"}, 0);
        table = new JTable(model);
        JScrollPane tablePane = new JScrollPane(table);

        // Sắp xếp layout: nhập liệu ở trên, nút ở giữa, bảng ở dưới
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(tablePane, BorderLayout.CENTER);

        txtTenDangNhap.setEditable(true);
    }

    // Các listener cho controller
    public void addThemListener(ActionListener l)    { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l)     { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l)     { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l)  { btnLamMoi.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }
    public void addTableSelectionListener(ListSelectionListener l) { table.getSelectionModel().addListSelectionListener(l); }

    public TaiKhoan getTaiKhoanFromForm() {
        return new TaiKhoan(txtTenDangNhap.getText(), txtMatKhau.getText(), txtVaiTro.getText());
    }
    public String getTenDangNhap() { return txtTenDangNhap.getText(); }
    public String getTuKhoaTimKiem() { return txtTimKiem.getText(); }

    public void setTableData(List<TaiKhoan> list) {
        model.setRowCount(0);
        for (TaiKhoan tk : list) {
            model.addRow(new Object[]{tk.getTenDangNhap(), tk.getMatKhau(), tk.getVaiTro()});
        }
    }
    public void fillForm() {
        int i = table.getSelectedRow();
        if (i >= 0) {
            txtTenDangNhap.setText(model.getValueAt(i, 0).toString());
            txtMatKhau.setText(model.getValueAt(i, 1).toString());
            txtVaiTro.setText(model.getValueAt(i, 2).toString());
            txtTenDangNhap.setEditable(false);
        }
    }
    public void clearForm() {
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        txtVaiTro.setText("");
        txtTenDangNhap.setEditable(true);
        table.clearSelection();
    }
}