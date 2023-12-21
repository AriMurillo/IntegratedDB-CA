/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ari
 */
public class GUI {
    
    public void useGUI(){
        int i = 0;
        
        do{
            try{
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
                        i++;
                        } else {
                            System.out.println("Login failed. Incorrect username or password.");
                        }

                    case 2:
                        System.out.println("Please enter the username you will like to use and a password for your new account:");
                        //Databse validation still pending
                        i++;
                        break;
                        
                    default:
                        System.out.println("Oh uh, that was not an available option, try again.");
                }
          
            }catch(Exception e){
                System.out.println("Oh uh, that was not a number, try again.");
            }  
        }while(i == 0);
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
