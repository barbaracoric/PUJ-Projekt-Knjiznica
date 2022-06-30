package login.frames;

import ebhor.frames.RegisterFrame;
import ebhor.model.User;
import login.dao.LoginDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel userNameLable;
    JLabel passwordLabel;
    JTextField userNameTextField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    Container container;

    public LoginFrame() {
        userNameLable = new JLabel("User Name");
        userNameTextField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListener();

    }

    public void setBounds() {
        userNameLable.setBounds(10, 10, 100, 30);
        userNameTextField.setBounds(100, 10, 200, 30);
        passwordLabel.setBounds(10, 50, 100, 30);
        passwordField.setBounds(100, 50, 200, 30);
        loginButton.setBounds(100, 100, 200, 30);
        registerButton.setBounds(100, 200, 150, 20);
    }

    public void addComponents() {
        container.add(userNameLable);
        container.add(userNameTextField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(loginButton);
        container.add(registerButton);
    }

    public void addActionListener() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userName = userNameTextField.getText();
            String password = passwordField.getText();
            System.out.println(userName + " " + password);
            System.out.println(userName.length() + " " + password.length());
            Validation v = new Validation();
            java.util.List<String> errors = v.validateLogin(userName, password);
            if (errors.size() > 0) {
                JOptionPane.showMessageDialog(null, errors.toArray());
                return;
            }
            LoginDAO dao = new LoginDAO();
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            Boolean response = dao.checkLogin(user);
            if (Boolean.FALSE.equals(response)) {
                JOptionPane.showMessageDialog(null, "Wrong username or password ");
                return;
            }else{
                login.frames.HomeFrame homeFrame = new login.frames.HomeFrame();
                homeFrame.setTitle("Home page");
                homeFrame.setVisible(true);
                homeFrame.setBounds(270, 270, 370, 300);
                homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                homeFrame.setResizable(true);
                this.setVisible(false);
            }
        }
        if (e.getSource() == registerButton){
            RegisterFrame frame = new RegisterFrame();
            frame.setTitle("Register Form");
            frame.setVisible(true);
            frame.setBounds(270, 270, 370, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
        }
    }

    public static void main(String[] args) {
        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(250, 250, 370, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

    }

}
