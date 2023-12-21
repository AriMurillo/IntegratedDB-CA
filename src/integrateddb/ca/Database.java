/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ari
 */
public class Database{
    private static final String DB_USER = "ooc2023";
    private static final String DB_PASSWORD = "ooc2023";
    private static final String DB_URL = "jdbc:mysql://localhost/taxes_company";
    
    //List that contains all the users
    //Getter for the users list
    public static Users getUsers(String username, String password) {
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();){
            // Object that stores the result of our query
            String TABLE_NAME = "users";
            ResultSet results = stmt.executeQuery(String.format(
                    "SELECT * FROM users WHERE username='%s' and password='%s';",
                    username,password));
            if(!results.next()) {
                return null;
            }else{
            //results.next();
            String firstName = results.getString("first_name");
            String lastName = results.getString("last_name");
            username = results.getString("username");
            password = results.getString("password");
            String gender = results.getString("gender");
            String email = results.getString("email");
            String maritalStatus = results.getString("marital_status");
            boolean bothWork = results.getBoolean("if_married_both_work");
            boolean children = results.getBoolean("children");
            boolean userType = results.getBoolean("admin");
            int employeeID = results.getInt("employee_id");
            int id = results.getInt("user_id");
            Users users = new Users(username,password,firstName,lastName,gender, email,maritalStatus, bothWork, children,id,employeeID,userType);
            return users;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
