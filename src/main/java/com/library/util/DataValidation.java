package com.library.util;

import com.library.MainController;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataValidation {
    public static boolean signUpValidation(TextField fullNameInput, TextField usernameInput, TextField passcodeInput) {
        MainController mc = new MainController();

        Boolean result = false;

        String fullName = fullNameInput.getText();
        String username = usernameInput.getText();
        String passcode = passcodeInput.getText();

        //Full name validation
        if (fullName.isBlank() || fullName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Full Name is required.");
        } else {
            String[] nameParts = fullName.split("\\s+");
            if (nameParts.length < 2) {
                JOptionPane.showMessageDialog(null, "Full Name is required.");
            } else {
                System.out.println("Full name validated.");
                //Username validation
                if (username.isBlank() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username is required.");
                } else {
                    System.out.println("Username validated.");
                    //Passcode validation
                    //@todo ADD EXTRA PASSCODE VALIDATION: NUMBERS, CAPITAL LETTERS, SYMBOLS
                    if (passcode.isBlank() || passcode.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Passcode is required.");
                    } else {
                        System.out.println("Passcode validated.");
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    public static boolean loginValidation(TextField username, TextField passcode){
        MainController mc = new MainController();

        Boolean result = false;

        String usernameText = username.getText();
        String passcodeText = passcode.getText();

        //Username validation
        if (usernameText.isBlank() || usernameText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username is required.");
        } else {
            System.out.println("Username validated.");
            //Passcode validation
            if (passcodeText.isBlank() || passcodeText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Passcode is required.");
            } else {
                System.out.println("Passcode validated.");
                result = true;
            }
        }
        if (result) {
            result = false;
            try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
                System.out.println("Database connected.");
                Statement statement = connection.createStatement();
                //Check if username is in the database
                ResultSet databaseUser = statement.executeQuery("SELECT COUNT(MemberID) AS TotalUsers FROM tblMembers WHERE Username = '" + usernameText + "'");
                databaseUser.next();
                int totalUsers = databaseUser.getInt("TotalUsers");
                if (totalUsers == 1) {
                    //If the username is in the database, then check the passcode
                    ResultSet databasePasscode = statement.executeQuery("SELECT Passcode FROM tblMembers WHERE Username = '" + usernameText + "'");
                    databasePasscode.next();
                    String storedPasscode = databasePasscode.getString("Passcode");
                    if (passcodeText.equals(storedPasscode)) {
                        result = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Passcode.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username not found.");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
