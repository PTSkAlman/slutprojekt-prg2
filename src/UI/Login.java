package UI;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    //private String username, password;
    private JPanel panel;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton login, create;

    public Login() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(180,20));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(180,20));

        login = new JButton("Login");
        create = new JButton("Create Account");

        panel.add(usernameLabel,c);
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(usernameField,c);
        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(passwordLabel,c);
        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(passwordField,c);
        c.gridy = 4;
        c.gridwidth = 1;
        panel.add(login,c);
        c.gridx = 1;
        panel.add(create,c);

        this.add(panel);
        this.setPreferredSize(new Dimension(400,400));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public String getUsername() {
        return usernameField.getText();
    }
    public String getPassword() {
        return passwordField.getText();
    }
    public JButton getLogin() {
        return login;
    }
    public JButton getCreate() {
        return create;
    }
}
