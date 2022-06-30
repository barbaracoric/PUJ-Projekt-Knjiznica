package ebhor.dao;

import ebhor.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    Connection con = null;
    PreparedStatement ps = null;
    int rs;
    public User addStudent(User user) {

        con = login.dao.ConnectionFactory.getConnection();
        try {
            String insert = "INSERT INTO user (userName, password, firstName, lastName) VALUES (?, ?, ?, ?);";
            ps = con.prepareStatement(insert);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
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
        return user;
    }
}
