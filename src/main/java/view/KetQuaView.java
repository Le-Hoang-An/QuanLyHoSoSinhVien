package view;

import model.KetQua;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class KetQuaView extends JFrame {
    private JTextField txtMaSV, txtMaMon, txtDiem, txtTimKiem;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnTimKiem;
    private JTable table;
    private DefaultTableModel model;

    public KetQuaView() {
        setTitle("Quản lý kết quả");
        setSize(750, 450);
        setLocationRelativeTo(null);

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("Mã sinh viên:"));
        txtMaSV = new JTextField();
        inputPanel.add(txtMaSV);

        inputPanel.add(new JLabel("Mã môn học:"));
        txtMaMon = new JTextField();
        inputPanel.add(txtMaMon);

        inputPanel.add(new JLabel("Điểm:"));
        txtDiem = new JTextField();
        inputPanel.add(txtDiem);

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
        model = new DefaultTableModel(new String[]{"Mã SV", "Mã Môn", "Điểm"}, 0);
        table = new JTable(model);
        JScrollPane tablePane = new JScrollPane(table);

        // Sắp xếp layout: nhập liệu ở trên, nút ở giữa, bảng ở dưới
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(btnPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(tablePane, BorderLayout.CENTER);

        txtMaSV.setEditable(true);
        txtMaMon.setEditable(true);
    }

    // Các listener cho controller
    public void addThemListener(ActionListener l)    { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l)     { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l)     { btnXoa.addActionListener(l); }
    public void addLamMoiListener(ActionListener l)  { btnLamMoi.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }
    public void addTableSelectionListener(ListSelectionListener l) { table.getSelectionModel().addListSelectionListener(l); }

    public KetQua getKetQuaFromForm() {
        try {
            return new KetQua(
                txtMaSV.getText(),
                txtMaMon.getText(),
                Float.parseFloat(txtDiem.getText())
            );
        } catch (Exception ex) {
            return null;
        }
    }
    public String getMaSV() { return txtMaSV.getText(); }
    public String getMaMon() { return txtMaMon.getText(); }
    public String getTuKhoaTimKiem() { return txtTimKiem.getText(); }

    public void setTableData(List<KetQua> list) {
        model.setRowCount(0);
        for (KetQua kq : list) {
            model.addRow(new Object[]{kq.getMaSv(), kq.getMaMon(), kq.getDiem()});
        }
    }
    public void fillForm() {
        int i = table.getSelectedRow();
        if (i >= 0) {
            txtMaSV.setText(model.getValueAt(i, 0).toString());
            txtMaMon.setText(model.getValueAt(i, 1).toString());
            txtDiem.setText(model.getValueAt(i, 2).toString());
            txtMaSV.setEditable(false);
            txtMaMon.setEditable(false);
        }
    }
    public void clearForm() {
        txtMaSV.setText("");
        txtMaMon.setText("");
        txtDiem.setText("");
        txtMaSV.setEditable(true);
        txtMaMon.setEditable(true);
        table.clearSelection();
    }
}