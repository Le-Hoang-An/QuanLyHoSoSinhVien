package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin, btnThoat;

    public LoginView() {
        setTitle("Đăng nhập");
        setSize(350, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Tên đăng nhập:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("Mật khẩu:"));
        txtPass = new JPasswordField();
        panel.add(txtPass);

        btnLogin = new JButton("Đăng nhập");
        btnThoat = new JButton("Thoát");
        panel.add(btnLogin);
        panel.add(btnThoat);

        add(panel);

        // Để controller add listener
    }

    public String getUsername() {
        return txtUser.getText().trim();
    }

    public String getPassword() {
        return new String(txtPass.getPassword());
    }

    public void addLoginListener(ActionListener l) {
        btnLogin.addActionListener(l);
    }
    public void addThoatListener(ActionListener l) {
        btnThoat.addActionListener(l);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void clearFields() {
        txtUser.setText("");
        txtPass.setText("");
    }
}