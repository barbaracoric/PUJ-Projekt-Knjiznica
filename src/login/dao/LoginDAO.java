package login.dao;

import login.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;//status

    public Boolean checkLogin(User user) {
        con = ConnectionFactory.getConnection();
        try {
            String query = "select * from user where userName=? and password=?";
            ps = con.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Login success");
                user.setUser_id(rs.getInt("user_id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
