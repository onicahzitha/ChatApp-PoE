/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poeapk;

/**
 *
 * @author Student
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
        
    }
}
