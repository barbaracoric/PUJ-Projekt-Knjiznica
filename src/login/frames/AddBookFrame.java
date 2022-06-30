package ebhor.frames;

import ebhor.dao.BookDAO;
import ebhor.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookFrame extends JFrame implements ActionListener {
    JLabel titleLable;
    JLabel authorLabel;
    JLabel categoryLabel;
    JTextField titleTextField;
    JTextField authorField;
    JTextField categoryField;
    JButton addButton;
    Container container;

    public AddBookFrame(){
        titleLable = new JLabel("Title");
        titleTextField = new JTextField();
        authorLabel = new JLabel("Author");
        authorField = new JTextField();
        categoryLabel = new JLabel("Category");
        categoryField = new JTextField();
        addButton = new JButton("Add");
        container = getContentPane();
        container.setLayout(null);
        setBounds();
        addComponents();
        addActionListener();
    }

    public void setBounds() {
        titleLable.setBounds(10, 10, 100, 30);
        titleTextField.setBounds(100, 10, 200, 30);
        authorLabel.setBounds(10, 50, 100, 30);
        authorField.setBounds(100, 50, 200, 30);
        categoryLabel.setBounds(10, 90, 100, 30);
        categoryField.setBounds(100, 90, 200, 30);
        addButton.setBounds(100, 150, 150, 20);
    }

    public void addComponents() {
        container.add(titleLable);
        container.add(titleTextField);
        container.add(authorLabel);
        container.add(authorField);
        container.add(categoryLabel);
        container.add(categoryField);
        container.add(addButton);
    }

    public void addActionListener() {
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            Book book = new Book();
            book.setTitle(titleTextField.getText());
            book.setAuthor(authorField.getText());
            book.setCategory(categoryField.getText());
            new BookDAO().addBook(book);
            JOptionPane.showMessageDialog(null, "Success added book "+titleTextField.getText());
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Add book ");
        frame.setVisible(true);
        frame.setBounds(250, 250, 370, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

    }
}
