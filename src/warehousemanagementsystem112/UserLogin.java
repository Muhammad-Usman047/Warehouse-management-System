package warehousemanagementsystem112;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UserLogin implements ActionListener {
    JFrame frame;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, closeButton;
    JLabel label, labelPass;

    public UserLogin(String title) {
        this.frame = new JFrame(title);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        closeButton = new JButton("Close");

        loginButton.setBackground(new Color(0, 0, 50));
        loginButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(0, 0, 50));
        closeButton.setForeground(Color.WHITE);

        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        label = new JLabel("Username:");
        labelPass = new JLabel("Password:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        labelPass.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(label);
        panel.add(usernameField);
        panel.add(labelPass);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(closeButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(this);
        closeButton.addActionListener(this);
    }

    public abstract void actionPerformed(ActionEvent e);
}