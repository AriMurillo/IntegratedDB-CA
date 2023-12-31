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
            ResultSet results = stmt.executeQuery(String.format(
                    "SELECT * FROM users WHERE username='%s' and password='%s';",
                    username,password));
            if(!results.next()) {
                return null;
            }else{
            String firstName = results.getString("first_name");
            String lastName = results.getString("last_name");
            username = results.getString("username");
            password = results.getString("password");
            String gender = results.getString("gender");
            String email = results.getString("email");
            String maritalStatus = results.getString("marital_status");
            boolean bothWork = results.getBoolean("if_married_both_work");
            boolean children = results.getBoolean("children");
            boolean userType = results.getBoolean("user_type");
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

    public static void insertUser(Users user) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String query = String.format(
                    "INSERT INTO users (username, user_id, first_name, last_name, password, gender, email, marital_status, if_married_both_work, children, admin, employee_id) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    user.getUsername(), user.getId(), user.getFirstName(), user.getLastName(), user.getPassword(),
                    user.getGender(), user.getEmail(), user.getMarital_status(), user.isIf_married_both_work(),
                    user.isChildren(), user.getUserType(), user.getEmployeeID());

            stmt.executeUpdate(query);
            System.out.println("User inserted into the database.");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements.
        }
    }
    public static ArrayList<Taxes> getTaxList() {
        ArrayList<Taxes> taxList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery("SELECT * FROM users;")) {

            while (results.next()) {
                int calculation_id = results.getInt("calculation_id");
                String taxes_type = results.getString("taxes_type");
                int id = results.getInt("user_id");
                String status = results.getString("status");
                int income = results.getInt("income");
                int taxes_owed = results.getInt("taxes_owed");
                int taxes_credit = results.getInt("taxes_credit");
                Taxes tax = new Taxes(calculation_id,taxes_type,id,status,income, taxes_owed,taxes_credit);
                taxList.add(tax);
            }
            return taxList;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Users> getUsersList() {
        ArrayList<Users> userList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery("SELECT * FROM users;")) {

            while (results.next()) {
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String username = results.getString("username");
                String password = results.getString("password");
                String gender = results.getString("gender");
                String email = results.getString("email");
                String maritalStatus = results.getString("marital_status");
                boolean bothWork = results.getBoolean("if_married_both_work");
                boolean children = results.getBoolean("children");
                boolean userType = results.getBoolean("admin");
                int employeeID = results.getInt("employee_id");
                int id = results.getInt("user_id");

                Users user = new Users(username, password, firstName, lastName, gender, email, maritalStatus, bothWork, children, id, employeeID, userType);
                userList.add(user);
            }

            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeUser(String usernameToRemove) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String query = String.format("DELETE FROM users WHERE username ='%s'", usernameToRemove);

            int affectedRows = stmt.executeUpdate(query);

            if (affectedRows > 0) {
                System.out.println("User removed successfully!");
            } else {
                System.out.println("User not found or removal failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

     public static Taxes getTaxes(int idUser) {
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();){
            // Object that stores the result of our query
            ResultSet results = stmt.executeQuery(String.format(
                    "SELECT * FROM calculations WHERE user_id='%s';",
                    idUser));
            if(!results.next()) {
                return null;
            }else{
            int calculation_id = results.getInt("calculation_id");
            String taxes_type = results.getString("taxes_type");
            int id = results.getInt("user_id");
            String status = results.getString("status");
            int income = results.getInt("income");
            int taxes_owed = results.getInt("taxes_owed");
            int taxes_credit = results.getInt("taxes_credit");
            Taxes taxes = new Taxes(calculation_id,taxes_type,id,status,income, taxes_owed,taxes_credit);
            return taxes;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


