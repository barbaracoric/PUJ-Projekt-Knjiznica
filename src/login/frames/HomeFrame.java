package login.frames;

import login.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class HomeFrame extends JFrame implements ActionListener {

    public static User currentUser;
    public static LoginFrame loginFrame;
    JLabel message;
    Container container = getContentPane();
    JButton viewBooks;
    JButton addBook;
    JButton logOut;
    JFrame frame2 = new JFrame("Books ");
    int initialisedTable = 0;

    public HomeFrame(User currentUser, LoginFrame loginFrame) throws IOException {
        this.currentUser = currentUser;
        this.loginFrame = loginFrame;
        message = new JLabel("You are successfully logged in, "
                + currentUser.getFirstName() + " "
                + currentUser.getLastName() + " ("
                + currentUser.getUserName() + ")");
        container.setLayout(null);
        viewBooks = new JButton("Show books");
        addBook = new JButton("Add book");
        logOut = new JButton("Log Out");
        this.setTitle("Home Form");
        this.setVisible(true);
        this.setBounds(250, 250, 500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.black.brighter());

        setPreferredSize(new Dimension(850,500));
        setMinimumSize(new Dimension(850,500));

        final BufferedImage image = ImageIO.read(new File("src/images/library_home.jpg"));

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        pane.setBounds(0,0,500,500);
        container.add(pane);
        setBounds();
        addComponents();
        addActionListener();
    }

    public void setBounds() {
        message.setBounds(510, 10, 500, 30);
        viewBooks.setBounds(580, 100, 200, 30);
        addBook.setBounds(580, 150, 200, 30);
        logOut.setBounds(580, 200, 200, 30);

    }

    public void addComponents() {
        container.add(message);
        container.add(viewBooks);
        container.add(addBook);
        container.add(logOut);
    }

    public void addActionListener() {
        viewBooks.addActionListener(this);
        addBook.addActionListener(this);
        logOut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewBooks) {
            addTableView();
        }
        if(e.getSource() == addBook){
            addBook();
        }
        if(e.getSource() == logOut){
            logOut();
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
        //frame2.setVisible(false);
        frame2.setLayout(new FlowLayout());
        frame2.setSize(800, 400);
        DefaultTableModel defaultTableModel = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                if(column == 3) return true;
                return false;
            }
        };;
        JTable table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(750, 200));
        table.setFillsViewportHeight(false);
        JButton returnBook = new JButton("Return Book");
        returnBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnBook.addActionListener(e -> {
            Connection connection = login.dao.ConnectionFactory.getConnection();
            String query1 = "update book set user_id = NULL where user_id = ? ";
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement(query1);
                ps.setString(1, String.valueOf(currentUser.getUser_id()));
                ps.executeUpdate();
                this.addTableView();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        //frame2.remove(1);

        if(initialisedTable == 1){
            JRootPane framePane = (JRootPane) frame2.getComponent(0);
            JLayeredPane layeredPane = (JLayeredPane) framePane.getComponent(1);
            JPanel jPane = (JPanel) layeredPane.getComponent(0);
            jPane.removeAll();
            jPane.add(new JScrollPane(table));
            frame2.add(returnBook);
        }
        else {
            frame2.add(new JScrollPane(table));
            frame2.add(returnBook);
        }
        defaultTableModel.addColumn("Title");
        defaultTableModel.addColumn("Author");
        defaultTableModel.addColumn("Category");
        defaultTableModel.addColumn("Rent");
        defaultTableModel.setColumnCount(4);
        table.getColumn("Rent").setCellRenderer(new ButtonRenderer());
        table.getColumn("Rent").setCellEditor(new ButtonEditor(new JCheckBox(), this));
        try {


            Connection connection = login.dao.ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from book ";
            ResultSet resultSet = statement.executeQuery(query);

            String query1 = "select count(*) as rentCount from book where user_id = ? ";
            PreparedStatement ps = connection.prepareStatement(query1);
            ps.setString(1, String.valueOf(currentUser.getUser_id()));
            ResultSet resultSet1 = ps.executeQuery();

            resultSet1.next();
            int isRented = resultSet1.getInt("rentCount");
            returnBook.setEnabled(isRented == 1);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                JButton rentButton = new JButton("Rent");
                Integer user_id = resultSet.getInt("user_id");
                rentButton.setEnabled(resultSet.wasNull() && isRented == 0);
                rentButton.setText(String.valueOf(resultSet.getInt("book_id")));
                defaultTableModel.addRow(new Object[]{title, author, category, rentButton});//Adding row in Table
                if(user_id == currentUser.getUser_id()){
                    JLabel jlabel = new JLabel("Rented book: " + title);
                    jlabel.setFont(new Font("Verdana",1,12));
                    jlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    frame2.add(jlabel);
                }
            }
            frame2.setVisible(true);//Setting the visibility of second Frame
            initialisedTable = 1;
        }catch (Exception e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }

    }
    public void logOut(){
        loginFrame.setVisible(true);
        this.setVisible(false);
    }
}


