package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.SinhVien;

public class SinhVienView extends JFrame {
    private JTextField txtMaSV, txtHoTen, txtGioiTinh, txtNgaySinh, txtMaLop, txtDiaChi, txtSearch;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnBack;
    private JTable table;

    public SinhVienView() {
        setTitle("Quản lý Sinh viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        txtMaSV = new JTextField();
        txtHoTen = new JTextField();
        txtGioiTinh = new JTextField();
        txtNgaySinh = new JTextField();
        txtMaLop = new JTextField();
        txtDiaChi = new JTextField();

        inputPanel.add(new JLabel("Mã SV:"));
        inputPanel.add(txtMaSV);
        inputPanel.add(new JLabel("Họ tên:"));
        inputPanel.add(txtHoTen);
        inputPanel.add(new JLabel("Giới tính:"));
        inputPanel.add(txtGioiTinh);
        inputPanel.add(new JLabel("Ngày sinh:"));
        inputPanel.add(txtNgaySinh);
        inputPanel.add(new JLabel("Mã lớp:"));
        inputPanel.add(txtMaLop);
        inputPanel.add(new JLabel("Địa chỉ:"));
        inputPanel.add(txtDiaChi);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        txtSearch = new JTextField(10);
        btnTimKiem = new JButton("Tìm kiếm");
        btnBack = new JButton("Quay lại");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(txtSearch);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnBack); // Thêm nút quay lại

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
    public String getHoTen() { return txtHoTen.getText(); }
    public String getGioiTinh() { return txtGioiTinh.getText(); }
    public String getNgaySinh() { return txtNgaySinh.getText(); }
    public String getMaLop() { return txtMaLop.getText(); }
    public String getDiaChi() { return txtDiaChi.getText(); }
    public String getTuKhoaTim() { return txtSearch.getText(); }
    public JTable getTable() { return table; }

    // Clear form
    public void clearForm() {
        txtMaSV.setText("");
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtNgaySinh.setText("");
        txtMaLop.setText("");
        txtDiaChi.setText("");
    }

    // Load bảng
    public void loadTable(List<SinhVien> list) {
        String[] col = {"Mã SV", "Họ tên", "Giới tính", "Ngày sinh", "Mã lớp", "Địa chỉ"};
        DefaultTableModel model = new DefaultTableModel(col, 0);
        for (SinhVien sv : list) {
            model.addRow(new Object[] {
                sv.getMaSv(), sv.getHoTen(), sv.getGioiTinh(), sv.getNgaySinh(), sv.getMaLop(), sv.getDiaChi()
            });
        }
        table.setModel(model);
    }

    // Listener
    public void addThemListener(ActionListener l) { btnThem.addActionListener(l); }
    public void addSuaListener(ActionListener l) { btnSua.addActionListener(l); }
    public void addXoaListener(ActionListener l) { btnXoa.addActionListener(l); }
    public void addTimKiemListener(ActionListener l) { btnTimKiem.addActionListener(l); }
    public void addBackListener(ActionListener l) { btnBack.addActionListener(l); }  // THÊM HÀM BACK
}
