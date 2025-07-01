package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.LopHoc;

public class LopHocView extends JFrame {
    private JTextField txtMaLop, txtTenLop, txtKhoa, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnBack;
    private JTable table;

    public LopHocView() {
        setTitle("Quản lý Lớp Học");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtMaLop = new JTextField();
        txtTenLop = new JTextField();
        txtKhoa = new JTextField();

        inputPanel.add(new JLabel("Mã lớp:"));
        inputPanel.add(txtMaLop);
        inputPanel.add(new JLabel("Tên lớp:"));
        inputPanel.add(txtTenLop);
        inputPanel.add(new JLabel("Khoa:"));
        inputPanel.add(txtKhoa);

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
    public String getMaLop() { return txtMaLop.getText(); }
    public String getTenLop() { return txtTenLop.getText(); }
    public String getKhoa() { return txtKhoa.getText(); }
    public String getTuKhoaTim() { return txtSearch.getText(); }
    public JTable getTable() { return table; }

    // Clear form
    public void clearForm() {
        txtMaLop.setText("");
        txtTenLop.setText("");
        txtKhoa.setText("");
    }

    // Load bảng
    public void loadTable(List<LopHoc> list) {
        String[] col = {"Mã lớp", "Tên lớp", "Khoa"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (LopHoc lh : list) {
            model.addRow(new Object[] {
                lh.getMaLop(), lh.getTenLop(), lh.getKhoa()
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
