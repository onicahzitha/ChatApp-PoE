/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poeapk;

/**
 *
 * @author Ncedolwenkosi Onicah Zitha
 */
import java.util.Scanner;
public class POEAPK {

    public static void main(String[] args) {
        // CREATING A SCANNER OBJECT 
        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();
        
        // DECLARATIONS
        String firstName;
        String lastName;
        String userName;
        String password;
        String phoneNumber;
        String loginUser;
        String loginPass; 
        
        // REGISTRATION
        System.out.println("--- User Registration ---");
        
        System.out.println("Enter First Name: ");
        firstName = input.nextLine();
        System.out.println("First Name successfully captured.");
        
        System.out.println("Enter Last Name: ");
        lastName = input.nextLine();
        System.out.println("Last name successfully captured");
        
        System.out.println("Enter Username (must contain '_' and be <=5): ");
        userName = input.nextLine();
        System.out.println("Username successfully captured.");
        
         System.out.println("Enter Password: (>= 8 characters, 1 uppercase, 1 lowercase, 1 special character.)");
        password = input.nextLine();
        System.out.println("Password successfully captured.");
        
         System.out.println("Enter SA Phone (+27...): ");
        phoneNumber = input.nextLine();
        System.out.println("Phone number successfully captured.");
        
        String regResult = loginSystem.registerUser(userName, password, phoneNumber);
        System.out.println(regResult);
    }
}