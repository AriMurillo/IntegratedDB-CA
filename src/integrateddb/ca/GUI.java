/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Ari
 */
public class GUI {

    public void useGUI(){
        int i = 0;


        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to 'Tax System' \n" +
                "The best application for your tax calculations, what would you like to do?: \n" +
                "1. Login \n" +
                "2. Register");
        int choice1 = sc.nextInt();

        switch(choice1){
            case 1:
                System.out.println("Please enter your username and password: ");
                Users user = authenticateUser(sc.next(), sc.next()); //Here is where the method is being used to identify the user
                if (user != null) {
                    String userName = user.getUsername();
                    System.out.println("Login successful. User Name: " + userName);
                    //i++;
                    if(user.getUserType() == true){
                        System.out.println("What would you like to do?" +
                                "1. Update your profile \n" +
                                "2. Remove users \n" +
                                "3. List of users \n" +
                                "4. Review users information");
                        int action1 = sc.nextInt();
                        switch(action1){
                            case 1:
                                System.out.println("Enter new profile information");
                                System.out.println("First Name: ");
                                String newFirstName = sc.next();
                                System.out.println("Last Name: ");
                                String newLastName = sc.next();
                                System.out.println("Username: ");
                                String newUsername = sc.next();
                                System.out.println("Password: ");
                                String newPassword = sc.next();
                            case 2:
                                System.out.println("Enter the username of the user to be removed: ");
                                String usernameToRemove = sc.next();
                                Database.removeUser(usernameToRemove);

                        }
                    } else{
                        System.out.println("What would you like to do?\n" +
                                "1. Update your Profile \n" +
                                "2. Check your Taxes \n" +
                                "3. Review your Information");
                        int action1 = sc.nextInt();
                        switch(action1){
                            case 1:
                                System.out.println("Enter new profile information");
                                System.out.println("First Name: ");
                                String newFirstName = sc.next();
                                System.out.println("Last Name: ");
                                String newLastName = sc.next();
                                System.out.println("Username: ");
                                String newUsername = sc.next();
                                System.out.println("Password: ");
                                String newPassword = sc.next();
                        }
                    }
                }else {
                    System.out.println("Login failed. Incorrect username or password.");
                }

            case 2:
                System.out.println("Please enter the Details for your new account:|username|password|first_name|last_name|gender|email|marital_status|if_married_both_work|children|Id|employee_id|userType|");
                Users newUser =  new Users(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.nextBoolean(), sc.nextBoolean(), sc.nextInt() , sc.nextInt() ,sc.nextBoolean());
                Database.insertUser(newUser);

            default:
                System.out.println("Oh uh, that was not an available option, try again.");
        }


    }
    //This is the method where is identify the type of user that is going to login
    private Users authenticateUser(String username, String password) {
        Users user = Database.getUsers(username,password);
        if (user == null){
            System.out.println("User Not Found");
            return null;
        }
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user; //The user was found
        }else{
            return null; //The user wasn't found
        }
    }

}
