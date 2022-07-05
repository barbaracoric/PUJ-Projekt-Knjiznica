package login.dao;

import login.model.Book;
import login.model.User;

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
    public Boolean rentBook(Book book, User user) {
        System.out.println(book.getBook_id() + ":" + user.getUser_id());
        con = login.dao.ConnectionFactory.getConnection();
        try {
            String update = "UPDATE book SET user_id = ? WHERE book_id = ?;";
            ps = con.prepareStatement(update);
            ps.setString(1, String.valueOf(user.getUser_id()));
            ps.setString(2, String.valueOf(book.getBook_id()));
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
