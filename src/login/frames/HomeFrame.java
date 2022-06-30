package login.frames;

import ebhor.frames.AddBookFrame;
import ebhor.frames.RegisterFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class HomeFrame extends JFrame implements ActionListener {

    JLabel message;
    Container container = getContentPane();
    JButton viewBooks;
    JButton addBook;

    public HomeFrame() {
        message = new JLabel("You are successfully logged in");
        container.setLayout(null);
        viewBooks = new JButton("Show books");
        addBook = new JButton("Add book");
        this.setTitle("Home Form");
        this.setVisible(true);
        this.setBounds(250, 250, 370, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.black.brighter());
        setBounds();
        addComponents();
        addActionListener();
    }

    public void setBounds() {
        message.setBounds(10, 10, 400, 30);
        viewBooks.setBounds(100, 100, 200, 30);
        addBook.setBounds(100, 200, 200, 30);

    }

    public void addComponents() {
        container.add(message);
        container.add(viewBooks);
        container.add(addBook);

    }

    public void addActionListener() {
        viewBooks.addActionListener(this);
        addBook.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewBooks) {
            addTableView();
        }
        if(e.getSource() == addBook){
            addBook();
        }
    }

    public void addBook(){
        AddBookFrame frame = new AddBookFrame();
        frame.setTitle("Add Book");
        frame.setVisible(true);
        frame.setBounds(270, 270, 370, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    public void addTableView(){
        JFrame frame2 = new JFrame("Books ");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(400, 400);
        frame2.setVisible(true);
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        JTable table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));
        defaultTableModel.addColumn("Title");
        defaultTableModel.addColumn("Author");
        defaultTableModel.addColumn("Category");
        try {


            Connection connection = login.dao.ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from book ";
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                defaultTableModel.addRow(new Object[]{title, author, category});//Adding row in Table
                frame2.setVisible(true);//Setting the visibility of second Frame

            }
        }catch (Exception e)
        {
            System.out.println("Error "+e.getMessage());
        }

    }

}


