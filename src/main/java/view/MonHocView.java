package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.MonHoc;

public class MonHocView extends JFrame {
    private JTextField txtMaMon, txtTenMon, txtSoTinChi, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnBack;
    private JTable table;

    public MonHocView() {
        setTitle("Quản lý Môn Học");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtMaMon = new JTextField();
        txtTenMon = new JTextField();
        txtSoTinChi = new JTextField();

        inputPanel.add(new JLabel("Mã môn:"));
        inputPanel.add(txtMaMon);
        inputPanel.add(new JLabel("Tên môn:"));
        inputPanel.add(txtTenMon);
        inputPanel.add(new JLabel("Số tín chỉ:"));
        inputPanel.add(txtSoTinChi);

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
    public String getMaMon() { return txtMaMon.getText(); }
    public String getTenMon() { return txtTenMon.getText(); }
    public String getSoTinChi() { return txtSoTinChi.getText(); }
    public String getTuKhoaTim() { return txtSearch.getText(); }
    public JTable getTable() { return table; }

    // Clear form
    public void clearForm() {
        txtMaMon.setText("");
        txtTenMon.setText("");
        txtSoTinChi.setText("");
    }

    // Load bảng
    public void loadTable(List<MonHoc> list) {
        String[] col = {"Mã môn", "Tên môn", "Số tín chỉ"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (MonHoc mh : list) {
            model.addRow(new Object[] {
                mh.getMaMon(), mh.getTenMon(), mh.getSoTinChi()
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
