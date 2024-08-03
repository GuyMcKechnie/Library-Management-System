package com.library.model;

import com.library.MainController;
import com.library.util.DataValidation;
import com.library.util.MemberDataModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;


public class Member {

    private static String username;
    private static String passcode;

    public static void initAddMemberView(Stage onboardingStage) { //Method used in MainController to initialize AddMemberView.fxml
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(Member.class.getResource("/com/library/view/AddMemberView.fxml"));
            Parent root = loader.load();

            // Create a new stage (window)
            Stage addMemberStage = new Stage();
            addMemberStage.setScene(new Scene(root));
            addMemberStage.setTitle("Add Member");

            //Add the icon
            Image icon = new Image(Member.class.getResourceAsStream("/com/library/image/icon.png"));
            addMemberStage.getIcons().add(icon);
            addMemberStage.show();
            MainController.setSignUpStage(addMemberStage);

            //Hide the OnboardingView
            onboardingStage.hide();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void addMember(TextField fullNameInput, TextField usernameInput, TextField passcodeInput, Stage signUpStage, Stage onboardingStage) {
        //Data validation
        Boolean isValid = DataValidation.signUpValidation(fullNameInput, usernameInput, passcodeInput);
        if (isValid) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
                System.out.println("Database connected.");
                //Open the database and check if the username is already in the database
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(MemberID) AS TotalUsers FROM tblMembers WHERE Username = '" + usernameInput.getText() + "'");
                resultSet.next();
                if (resultSet.getInt("TotalUsers") > 0) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                } else {
                    //Insert new member into the database

                    //Split full name into first and last name
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tblMembers (FirstName, LastName, Username, Passcode) VALUES (?,?,?,?)");
                    String[] names = fullNameInput.getText().split(" ");
                    preparedStatement.setString(1, names[0]);
                    preparedStatement.setString(2, names[1]);
                    preparedStatement.setString(3, usernameInput.getText());
                    preparedStatement.setString(4, passcodeInput.getText());
                    int numRowsInserted = preparedStatement.executeUpdate();
                    if (numRowsInserted > 0) {
                        System.out.println(numRowsInserted + " record(s) added.");
                    }
                    connection.close();
                    //Close the signUpView
                    signUpStage.close();
                    //Show the onboardingView
                    onboardingStage.show();
                }
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Data is not valid.");
        }

    }

    public static void initMainView(Stage onboardingStage, TextField lg_UsernameInput, TextField lg_PasscodeInput) {
        //@todo VALIDATION ON LOGIN
        Boolean isValid = DataValidation.loginValidation(lg_UsernameInput, lg_PasscodeInput);
        if (isValid) {
            onboardingStage.close();
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(Member.class.getResource("/com/library/view/MainView.fxml"));
                Parent root = loader.load();

                // Create a new stage (window)
                Stage addMemberStage = new Stage();
                addMemberStage.setScene(new Scene(root));
                addMemberStage.setTitle("Library Manager");

                //Add the icon
                Image icon = new Image(Member.class.getResourceAsStream("/com/library/image/icon.png"));
                addMemberStage.getIcons().add(icon);

                //Show the stage
                addMemberStage.show();

                //@todo ADD USERNAME TO USERNAME BUTTON

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //Can't you show the date when it was borrowed and that as well?
    @SuppressWarnings("unchecked")
    public static void initTable(@SuppressWarnings("rawtypes") TreeTableView memTreeTable) {
        //Open the database and assign all the data in the table to cells in the treeTable
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblMembers");
            //Remove existing data
            memTreeTable.getColumns().clear();
            //Add all the data from the table to the tree table
            TreeTableColumn<MemberDataModel, String> IDColumn = new TreeTableColumn<>("ID");
            IDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("MemberID"));
            TreeTableColumn<MemberDataModel, String> FirstNameColumn = new TreeTableColumn<>("First Name");
            FirstNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("FirstName"));
            TreeTableColumn<MemberDataModel, String> LastNameColumn = new TreeTableColumn<>("Last Name");
            LastNameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("LastName"));
            TreeTableColumn<MemberDataModel, String> UsernameColumn = new TreeTableColumn<>("Username");
            UsernameColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Username"));
            memTreeTable.getColumns().add(IDColumn);
            memTreeTable.getColumns().add(FirstNameColumn);
            memTreeTable.getColumns().add(LastNameColumn);
            memTreeTable.getColumns().add(UsernameColumn);
            TreeItem<MemberDataModel> rootItem = new TreeItem<>(new MemberDataModel("memIDRoot", "memFirstNameRoot", "memLastNameRoot", "memUsernameRoot"));
            rootItem.setExpanded(true);
            while (resultSet.next()) {
                String memID = resultSet.getString("MemberID");
                String memFirstName = resultSet.getString("FirstName");
                String memLastName = resultSet.getString("LastName");
                String memUsername = resultSet.getString("Username");
                setUsername(memUsername);
                TreeItem<MemberDataModel> item = new TreeItem<>(new MemberDataModel(memID, memFirstName, memLastName, memUsername));
                rootItem.getChildren().add(item);
            }
            memTreeTable.setRoot(rootItem);
            memTreeTable.setShowRoot(false);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUsername(String usernameInput) {
        username = usernameInput;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPasscode() {
        return passcode;
    }

    public static void setPasscode(String passcodeInput) {
        passcode = passcodeInput;
    }

    public static void initUserView(Label avUsername) {
        avUsername.setText(getUsername());
    }

    public static void changeUsername(String changedUsername, TextField usernameTextField) {
        usernameTextField.clear();
        String oldUsername = username;
        if (changedUsername.isBlank() || changedUsername.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty.");
            return;
        } else if (changedUsername.equals(oldUsername)) {
            JOptionPane.showMessageDialog(null, "Username cannot be the same.");
            return;
        }
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(MemberID) AS TotalUsers FROM tblMembers WHERE Username = '" + changedUsername + "'");
            resultSet.next();
            if (resultSet.getInt("TotalUsers") > 0) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
            } else {
                //Update the username in the database
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tblMembers SET Username =? WHERE Username =?");
                preparedStatement.setString(1, changedUsername);
                preparedStatement.setString(2, getUsername());
                int numRowsUpdated = preparedStatement.executeUpdate();
                if (numRowsUpdated > 0) {
                    System.out.println(numRowsUpdated + " record(s) updated.");
                }
                connection.close();
                JOptionPane.showMessageDialog(null, "Username changed successfully.");
                setUsername(changedUsername);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void changePasscode(String changedPasscode, TextField passcodeTextField) {
        passcodeTextField.clear();
        String oldPasscode = passcode;
        if(changedPasscode.isEmpty() || changedPasscode.isBlank()) {
            JOptionPane.showMessageDialog(null, "Passcode cannot be empty.");
            return;
        } else if (changedPasscode.equals(oldPasscode)) {
            JOptionPane.showMessageDialog(  null, "Passcode cannot be the same.");
        }
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(MemberID) AS TotalUsers FROM tblMembers WHERE Username = '" + getUsername() + "' AND Passcode = '" + changedPasscode + "'");
            resultSet.next();
            if (resultSet.getInt("TotalUsers") == 0) {
                //Update the passcode in the database
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tblMembers SET Passcode =? WHERE Username =?");
                preparedStatement.setString(1, changedPasscode);
                preparedStatement.setString(2, getUsername());
                int numRowsUpdated = preparedStatement.executeUpdate();
                if (numRowsUpdated > 0) {
                    System.out.println(numRowsUpdated + " record(s) updated.");
                }
                connection.close();
                JOptionPane.showMessageDialog(null, "Passcode changed successfully.");
                setPasscode(changedPasscode);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
