/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeapk;

/**
 *
 * @author Ncedolwenkosi Onicah Zitha 
 */
public class Login {
    
    private String storedUsername;
    private String storedPassword;
    private String storedPhone;
    
    // USERNAME VALIDATION
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length()>=5;
        
    }
    
    //PASSWORD VALIDATION
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()].*");
        boolean isLongEnough = password.length()>=8;
        
        return hasCapital && hasNumber && hasSpecial && isLongEnough;
         
    }
    
    //PHONE VALIDATION
    public boolean checkCellPhoneNumber(String phone){
        return phone.startsWith("+27") && phone.length() ==12;
        
    }
    
    //REGISTER USER
    public String registerUser(String username, String password, String phone) {
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted. It must contain an underscore and be less than 5 characters.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. Must be at least 8 characters, include a capital letter, number,and special character.";
            }
        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number is incorrectly formatted or missing country code.";
        }
        
        //STORE DETAILS
        storedUsername = username;
        storedPassword = password;
        storedPhone = phone; 
        
        return "User successfully captured.";
    }
    //LOGIN STATUS MESSAGE
    public String returnLoginStatus(boolean success, String firstName, String lastName) {
        if (success) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else{
            return "Username or password incorrect, please try again.";
        }
    }

   
        
    
}
 