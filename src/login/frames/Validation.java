/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.frames;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Validation {

    public List<String> validateLogin(String uname, String password) {
        ArrayList<String> err = new ArrayList<String>();
        if (uname.isEmpty()) {
            err.add("User Name can not be empty");
        } else if (uname.length() < 4) {
            err.add("User Name is too short");
        } else if (uname.length() > 20) {
            err.add("user Name is too long");
        }
        if (password.isEmpty()) {
            err.add("Password can not be empty");
        } else if (password.length() < 4) {
            err.add("Password is too short");
        } else if (password.length() > 20) {
            err.add("Password is too long");
        }
        return err;
    }
}
