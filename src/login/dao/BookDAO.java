package ebhor.dao;

import ebhor.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {
    Connection con = null;
    PreparedStatement ps = null;
    int rs;
    public Boolean addBook(Book book) {

        con = login.dao.ConnectionFactory.getConnection();
        try {
            String insert = "INSERT INTO book (title, author, category) VALUES (?, ?, ?);";
            ps = con.prepareStatement(insert);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
