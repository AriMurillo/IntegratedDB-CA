/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrateddb.ca;

/**
 *
 * @author Ari
 */
public class Admin {
    public Admin(String username, String password, String name, String surname) {
        super(username, password, name, surname, UserType.ADMIN);
    }
}
