package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.GiangVien;

public class GiangVienView extends JFrame {

    private JTextField txtMaGV, txtHoTen, txtHocVi, txtKhoa, txtSDT, txtEmail, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTim, btnBack;
    private JTable table;

    public GiangVienView() {
        setTitle("Quản lý Giảng Viên");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        txtMaGV = new JTextField();
        txtHoTen = new JTextField();
        txtHocVi = new JTextField();
        txtKhoa = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();

        inputPanel.add(new JLabel("Mã GV:"));
        inputPanel.add(txtMaGV);
        inputPanel.add(new JLabel("Họ tên:"));
        inputPanel.add(txtHoTen);
        inputPanel.add(new JLabel("Học vị:"));
        inputPanel.add(txtHocVi);
        inputPanel.add(new JLabel("Khoa:"));
        inputPanel.add(txtKhoa);
        inputPanel.add(new JLabel("SĐT:"));
        inputPanel.add(txtSDT);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(txtEmail);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        txtSearch = new JTextField(12);
        btnTim = new JButton("Tìm");
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

    // Getter các trường
    public String getMaGV() {
        return txtMaGV.getText();
    }

    public String getHoTen() {
        return txtHoTen.getText();
    }

    public String getHocVi() {
        return txtHocVi.getText();
    }

    public String getKhoa() {
        return txtKhoa.getText();
    }

    public String getSDT() {
        return txtSDT.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getTuKhoaTim() {
        return txtSearch.getText();
    }

    public JTable getTable() {
        return table;
    }

    // Clear form
    public void clearForm() {
        txtMaGV.setText("");
        txtHoTen.setText("");
        txtHocVi.setText("");
        txtKhoa.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
    }

    // Load dữ liệu
    public void loadTable(List<GiangVien> list) {
        String[] cols = {"Mã GV", "Họ tên", "Học vị", "Khoa", "SĐT", "Email"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for (GiangVien gv : list) {
            model.addRow(new Object[]{
                gv.getMaGV(), gv.getHoTen(), gv.getHocVi(), gv.getKhoa(), gv.getSoDienThoai(), gv.getEmail()
            });
        }
        table.setModel(model);
    }

    // Listener
    public void addThemListener(ActionListener l) {
        btnThem.addActionListener(l);
    }

    public void addSuaListener(ActionListener l) {
        btnSua.addActionListener(l);
    }

    public void addXoaListener(ActionListener l) {
        btnXoa.addActionListener(l);
    }

    public void addTimListener(ActionListener l) {
        btnTim.addActionListener(l);
    }

    public void addBackListener(ActionListener l) {
        btnBack.addActionListener(l);
    }
}
