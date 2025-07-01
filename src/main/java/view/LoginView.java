package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Đăng nhập");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        txtUser = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton("Đăng nhập");

        add(new JLabel("Tên đăng nhập:"));
        add(txtUser);
        add(new JLabel("Mật khẩu:"));
        add(txtPass);
        add(btnLogin);
    }

    public String getUsername() { return txtUser.getText(); }
    public String getPassword() { return String.valueOf(txtPass.getPassword()); }
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void addLoginListener(ActionListener listener) { btnLogin.addActionListener(listener); }
}
