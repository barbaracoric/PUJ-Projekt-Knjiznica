package ebhor.frames;

import ebhor.dao.UserDAO;
import ebhor.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {

    JLabel userNameLable;
    JLabel passwordLabel;
    JLabel firstNameLabel;
    JLabel lastNameLabel;
    JTextField userNameTextField;
    JTextField firstNameField;
    JTextField lastNameField;
    JPasswordField passwordField;
    JButton registerButton;
    Container container;

    public RegisterFrame(){
        firstNameLabel = new JLabel("First name");
        firstNameField = new JTextField();
        lastNameLabel = new JLabel("Last name");
        lastNameField = new JTextField();
        userNameLable = new JLabel("User Name");
        userNameTextField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        registerButton = new JButton("Register");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListener();
    }

    public void setBounds() {
        firstNameLabel.setBounds(10, 10, 100, 30);
        firstNameField.setBounds(100, 10, 200, 30);
        lastNameLabel.setBounds(10, 50, 100, 30);
        lastNameField.setBounds(100, 50, 200, 30);
        userNameLable.setBounds(10, 90, 100, 30);
        userNameTextField.setBounds(100, 90, 200, 30);
        passwordLabel.setBounds(10, 130, 100, 30);
        passwordField.setBounds(100, 130, 200, 30);
        registerButton.setBounds(100, 200, 150, 20);
    }

    public void addComponents() {
        container.add(firstNameLabel);
        container.add(firstNameField);
        container.add(lastNameLabel);
        container.add(lastNameField);
        container.add(userNameLable);
        container.add(userNameTextField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(registerButton);
    }

    public void addActionListener() {
        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton){
            User user = new User();
            user.setUserName(userNameTextField.getText());
            user.setPassword(passwordField.getText());
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            new UserDAO().addStudent(user);
            JOptionPane.showMessageDialog(null, "Success register "+firstNameField.getText());
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Register Form");
        frame.setVisible(true);
        frame.setBounds(250, 250, 370, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

    }

}
