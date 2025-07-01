package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.TaiKhoan;

public class TaiKhoanView extends JFrame {
    private JTextField txtTenDangNhap, txtMatKhau, txtVaiTro, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnBack;
    private JTable table;

    public TaiKhoanView() {
        setTitle("Quản lý Tài Khoản");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtTenDangNhap = new JTextField();
        txtMatKhau = new JTextField();
        txtVaiTro = new JTextField();

        inputPanel.add(new JLabel("Tên đăng nhập:"));
        inputPanel.add(txtTenDangNhap);
        inputPanel.add(new JLabel("Mật khẩu:"));
        inputPanel.add(txtMatKhau);
        inputPanel.add(new JLabel("Vai trò:"));
        inputPanel.add(txtVaiTro);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        txtSearch = new JTextField(10);
        btnTim = new JButton("Tìm kiếm");
        btnBack = new JButton("Quay lại");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(txtSearch);
        buttonPanel.add(btnTim);
        buttonPanel.add(btnBack);

        // Bảng
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // Add vào frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getter
    public String getTenDangNhap() { return txtTenDangNhap.getText(); }
    public String getMatKhau() { return txtMatKhau.getText(); }
    public String getVaiTro() { return txtVaiTro.getText(); }
    public String getTuKhoaTim() { return txtSearch.getText(); }
    public JTable getTable() { return table; }

    // Clear form
    public void clearForm() {
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
        txtVaiTro.setText("");
    }

    // Load bảng
    public void loadTable(List<TaiKhoan> list) {
        String[] col = {"Tên đăng nhập", "Mật khẩu", "Vai trò"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (TaiKhoan tk : list) {
            model.addRow(new Object[] {
                tk.getTenDangNhap(), tk.getMatKhau(), tk.getVaiTro()
            });
        }
        table.setModel(model);
    }

    // Listener
    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addTimListener(ActionListener l) { btnTim.addActionListener(l); }
    public void addBackListener(ActionListener l) { btnBack.addActionListener(l); } 
}
