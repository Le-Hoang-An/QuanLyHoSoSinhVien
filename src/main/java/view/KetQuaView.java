package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.KetQua;

public class KetQuaView extends JFrame {
    private JTextField txtMaSV, txtMaMon, txtDiem, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnBack;
    private JTable table;

    public KetQuaView() {
        setTitle("Quản lý Kết Quả Học Tập");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        txtMaSV = new JTextField();
        txtMaMon = new JTextField();
        txtDiem = new JTextField();

        inputPanel.add(new JLabel("Mã sinh viên:"));
        inputPanel.add(txtMaSV);
        inputPanel.add(new JLabel("Mã môn:"));
        inputPanel.add(txtMaMon);
        inputPanel.add(new JLabel("Điểm:"));
        inputPanel.add(txtDiem);

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
    public String getMaSV() { return txtMaSV.getText(); }
    public String getMaMon() { return txtMaMon.getText(); }
    public String getDiem() { return txtDiem.getText(); }
    public String getTuKhoaTim() { return txtSearch.getText(); }
    public JTable getTable() { return table; }

    // Clear form
    public void clearForm() {
        txtMaSV.setText("");
        txtMaMon.setText("");
        txtDiem.setText("");
    }

    // Load bảng
    public void loadTable(List<KetQua> list) {
        String[] col = {"Mã SV", "Mã môn", "Điểm"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (KetQua kq : list) {
            model.addRow(new Object[] {
                kq.getMaSv(), kq.getMaMon(), kq.getDiem()
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
