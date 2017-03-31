/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milindjuttiga.scorebot;

import static com.milindjuttiga.scorebot.Main.akeys;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milind
 */
public class DatabaseHelper {

    private String url = "jdbc:mysql://localhost:3306/scorebot";
    private String user = akeys.user_name;
    private String password = akeys.password;

    public void addUser(int id) {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("INSERT INTO USERS(User_id) VALUES(?)");
            pst.setString(1, Integer.toString(id));
            pst.executeUpdate();

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    public ArrayList<Integer> retrieveUsers() {
        ArrayList<Integer> users = new ArrayList<Integer>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            pst = con.prepareStatement("SELECT * FROM USERS");
            rs = pst.executeQuery();
            while (rs.next()) {
                users.add(rs.getInt(2));
            }
            return users;
            
        } catch (SQLException ex) {
            
            Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            return null;

        } finally {

            try {

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}