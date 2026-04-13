/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.loginandregistration;
        
   import java.util.Scanner;

/**
 *
 * @author Student
 */
public class LoginandRegistration {

        
     // Boolean validation check ---------------
    
    public static boolean checkUsername(String username){
        return username.length() <= 5 && username.contains("_");
    }
    
    public static boolean checkPasswordComplexity(String password) {
        if (password.length() <=8) return false;
        
        boolean hasCapital = false;
        boolean hasSpecialChar = false;
        boolean hasDigits = false;
        String specialCharacters = "!@#$%^&*()-_=+{}[]|;:',.<>?/`~";
        String Digits = "0123456789";
        
         for (char c : password.toCharArray()){
             if(Character.isUpperCase(c))     hasCapital = true;
             if (specialCharacters.indexOf(c) >= 0)  hasSpecialChar = true;
             if  ( Digits.indexOf(c) >= 0) hasDigits = true;
         }
         return hasCapital && hasSpecialChar && hasDigits;
    }
    public static boolean checkCellPhoneNUmber(String cellphone) {
        return cellphone.startsWith("+") && cellphone.length() >= 10;
        
    }
    
    //----------login check---------------
    public static boolean LoginUser(String RegisteredUsername, String RegisteredPassword,
    String LoginUsername, String LoginPassword) {
         return RegisteredUsername.equals(LoginUsername) && RegisteredPassword.equals(LoginPassword);
         
         
    }
    
    public static String returnLoginStatus(  String RegisteredUsername, String Firstname, String Lastname, String RegisteredPassword, String LoginUsername, String LoginPassword) {
    
        if (LoginUser(RegisteredUsername, RegisteredPassword,LoginUsername,LoginPassword)){
            return "Login successful! Welcome, " + Firstname + " " +Lastname +  " ,great to see you again!";
        }else{
            return "Login failed: incorrect username or password. Try again.";
        }
    }

    public static void main(String[] args) {

        String Username;
        String Password;
        String cellPhoneNumber;
        String Firstname;
        String Lastname;
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter first name: ");
        Firstname = input.next();
        
        System.out.print("Enter last name: ");
        Lastname = input.next();
        
        System.out.print("Enter Username: ");
        Username = input.next();

        System.out.print("Enter Password : ");
        Password = input.next();

        System.out.print("Enter Cellphone Number : ");
        cellPhoneNumber = input.next();

        System.out.println("\n------------------------------------");
        
        System.out.println("Firstname: " + Firstname );
        System.out.println("Lastname: " + Lastname);
        System.out.println("Username: " + Username);
        System.out.println("Password: " + Password);
        System.out.println("Cellphone Number: " + cellPhoneNumber);
        System.out.println("------------------------------------");

        // Username validation: max 5 characters AND must contain "_"
        if (Username.length() <= 5 && Username.contains("_")) {
            System.out.println(" Username successfully captured");
        } else {
            if (!Username.contains("_")) {
                System.out.println(" Invalid Username: must contain an underscore (_)");
            } else {
                System.out.println(" Invalid Username: must be 5 characters or fewer");
            }
        }

        // Password validation
        boolean hasCorrectLength = Password.length() <= 8;
        boolean hasCapital = false;
        boolean hasSpecialChar = false;
        boolean hasDigits = false;
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~";
        
        String Digits = "0123456789";
        
        for (char c : Password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (specialCharacters.indexOf(c) >= 0) {
                hasSpecialChar = true;
            }
            if(Digits.indexOf(c)>= 0){
             hasDigits =true;
           }
        }
            for(int i = 0; i < Password.length(); i++) {
            char c = Password.charAt(i);
        }

        if (hasCorrectLength && hasCapital && hasSpecialChar &&hasDigits) {
            System.out.println(" Password successfully captured");
        } else {
            System.out.println(" Invalid Password:");
            if (!hasCorrectLength) {
                System.out.println("   - Must be 8 characters or fewer");
            }
            if (!hasCapital) {
                System.out.println("   - Must contain at least one capital letter");
            }
            if (!hasSpecialChar) {
                System.out.println("   - Must contain at least one special character (e.g. @, #, !)");
            }
            if(!hasDigits) {
                System.out.println("Must contain atleast one digit(e.g.1,2,3,4,5");
            }
        }

        // Cellphone validation: at least 10 characters AND must start with "+"
            for (int i =1;i < cellPhoneNumber.length(); i++) {
        if (cellPhoneNumber.length() >= 10 && cellPhoneNumber.startsWith("+")) {
            System.out.println("cellphone number successfully added");
        } else {
            if (!cellPhoneNumber.startsWith("+")) {
                System.out.println(" Invalid cellphone number: must start with '+' (e.g. +27123456789)");
            } else {
                System.out.println(" Invalid cellphone number: must be at least 10 digits including '+'");
            }
        }
        String RegisteredUsername = Username;
        String RegisteredPassword = Password;
        
        
        //-----login details------
        String LoginUsername;
        String LoginPassword;
   System.out.println("\n---------login----------");
     System.out.print("Enter Username: ");
        LoginUsername = input.next();
        System.out.print("Enter Password: ");
        LoginPassword = input.next();
        System.out.println("\n----------------------");
       
        
        if(LoginUsername.equals(RegisteredUsername) &&LoginPassword.equals(RegisteredPassword)){
        System.out.println( "\n Login successful! Welcome, " + Firstname + " " + Lastname + " ,great to see you again!");
        }else{
            System.out.println("\n Login failed incorrect Username or Password try again.");
        }
        
        
   input.close();
    }

}
