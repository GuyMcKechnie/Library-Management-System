package com.library;

import com.library.model.Book;
import com.library.model.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;

public class MainController {

    @FXML
    public static Label su_fullName;

    public static Label getSu_fullName() {
        return su_fullName;
    }

    public static void setSu_fullName(Label su_fullName) {
        MainController.su_fullName = su_fullName;
    }

    @FXML
    static Stage onboardingStage;
    @FXML
    static Stage signUpStage;
    @FXML
    TextField fullNameInput;
    @FXML
    TextField usernameInput;
    @FXML
    TextField passcodeInput;
    @FXML
    Label su_username;
    @FXML
    Label su_passcode;
    @FXML
    Label ob_username;
    @FXML
    Label ob_passcode;
    @FXML
    AnchorPane mv_Overview;
    @FXML
    AnchorPane mv_Members;
    @FXML
    AnchorPane mv_Books;
    @FXML
    AnchorPane mv_BorrowedBooks;
    @FXML
    AnchorPane mv_Help;
    @FXML
    AnchorPane mv_User;
    @FXML
    TextField lg_UsernameInput;
    @FXML
    Label numBooks;
    @FXML
    Label numMembers;
    @FXML
    Label numBorrowedBooks;
    @SuppressWarnings("rawtypes")
    @FXML
    TreeTableView memTreeTable;
    @FXML
    Button mv_Username = new Button();
    @SuppressWarnings("rawtypes")
    @FXML
    TreeTableView bookTreeTable;

    @SuppressWarnings("rawtypes")
    @FXML
    TreeTableView borrowedBookTreeTable;

    @FXML
    TextField lg_PasscodeInput;

    @FXML
    Label av_Username;

    @FXML
    Label av_Passcode;

    public Label getAv_Username() {
        return av_Username;
    }

    public Label getAv_Passcode() {
        return av_Passcode;
    }

    @SuppressWarnings("unchecked")
    public TreeTableView<Book> getBorrowedBookTreeTable() {
        return (TreeTableView<Book>) borrowedBookTreeTable;
    }

    public Button getMvUsernameButton() {
        return mv_Username;
    }

    @SuppressWarnings("unchecked")
    public TreeTableView<Book> getBookTreeTable() {
        return (TreeTableView<Book>) bookTreeTable;
    }

    @SuppressWarnings("unchecked")
    public TreeTableView<Member> getMemTreeTable() {
        return (TreeTableView<Member>) memTreeTable;
    }

    public Label getNumBooks() {
        return numBooks;
    }

    public Label getNumMembers() {
        return numMembers;
    }

    public Label getNumBorrowedBooks() {
        return numBorrowedBooks;
    }

    public TextField getLg_UsernameInput() {
        return lg_UsernameInput;
    }

    public TextField getLg_PasscodeInput() {
        return lg_PasscodeInput;
    }

    public PieChart getMv_PieChart() {
        return mv_PieChart;
    }

    @FXML
    private PieChart mv_PieChart;
    Pane[] mvPanes = new Pane[] { mv_Overview, mv_Members, mv_Books, mv_User };

    public static Stage getOnboardingStage() {
        return onboardingStage;
    }

    public static void setOnboardingStage(Stage onboardingStage) {
        MainController.onboardingStage = onboardingStage;
    }

    public static Stage getSignUpStage() {
        return signUpStage;
    }

    public static void setSignUpStage(Stage signUpStage) {
        MainController.signUpStage = signUpStage;
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        try {
            // Run the initAddMemberView method in the Member class
            Member.initAddMemberView(getOnboardingStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddMember(ActionEvent event) {
        // Run the addMember method in the Member class
        Member.addMember(fullNameInput, usernameInput, passcodeInput, getSignUpStage(), getOnboardingStage());
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        // Run the login method in the Member class
        Member.setUsername(getLg_UsernameInput().getText());
        Member.setPasscode(getLg_PasscodeInput().getText());
        Member.initMainView(getOnboardingStage(), getLg_UsernameInput(), getLg_PasscodeInput());
    }

    @FXML
    private void handleMVUser(ActionEvent event) {
        mv_User.setVisible(true);
        mv_Books.setVisible(false);
        mv_Members.setVisible(false);
        mv_Overview.setVisible(false);
        mv_BorrowedBooks.setVisible(false);
        mv_Help.setVisible(false);
        Member.initUserView(getAv_Username());
    }

    @FXML
    private void handleMVMembers(ActionEvent event) {
        mv_Members.setVisible(true);
        mv_Books.setVisible(false);
        mv_User.setVisible(false);
        mv_Overview.setVisible(false);
        mv_BorrowedBooks.setVisible(false);
        mv_Help.setVisible(false);
        Member.initTable(getMemTreeTable());
    }

    @FXML
    private void handleMVBooks(ActionEvent event) {
        mv_Books.setVisible(true);
        mv_Members.setVisible(false);
        mv_User.setVisible(false);
        mv_Overview.setVisible(false);
        mv_BorrowedBooks.setVisible(false);
        mv_Help.setVisible(false);
        Book.initTable(getBookTreeTable());
    }

    @FXML
    private void handleMVBorrowed(ActionEvent event) {
        mv_Books.setVisible(false);
        mv_Members.setVisible(false);
        mv_User.setVisible(false);
        mv_Overview.setVisible(false);
        mv_BorrowedBooks.setVisible(true);
        mv_Help.setVisible(false);
        Book.initBorrowedBookTable(getBorrowedBookTreeTable());
    }

    @FXML
    private void handleHelp(ActionEvent event) {
        mv_Overview.setVisible(false);
        mv_Members.setVisible(false);
        mv_Books.setVisible(false);
        mv_User.setVisible(false);
        mv_Help.setVisible(true);
        mv_BorrowedBooks.setVisible(false);
    }

    @FXML
    private void handleMVOverview(ActionEvent event) {
        mv_Overview.setVisible(true);
        mv_Members.setVisible(false);
        mv_Books.setVisible(false);
        mv_User.setVisible(false);
        mv_Help.setVisible(false);
        mv_BorrowedBooks.setVisible(false);
    }

    @FXML
    private void initialize() {
        // Set the username to the button
        if (mv_Username != null) {
            mv_Username.setText(Member.getUsername());
        } else {
            System.out.println("mv_Username is null");
        }
        // Set overview items
        if (mv_PieChart != null) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager
                        .getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
                System.out.println("Database connected.");
                Statement statement = connection.createStatement();
                ResultSet totalBooksResult = statement.executeQuery("SELECT COUNT(BookID) AS TotalBooks FROM tblBooks");
                totalBooksResult.next();
                int totalBooks = totalBooksResult.getInt("TotalBooks");
                ResultSet authorResult = statement
                        .executeQuery("SELECT a.[Author Name], COUNT(b.BookID) AS NumberOfBooks " +
                                "FROM tblBooks b " +
                                "INNER JOIN tblAuthors a ON b.AuthorID = a.[Author ID] " +
                                "GROUP BY a.[Author Name]");
                mv_PieChart.getData().clear();
                while (authorResult.next()) {
                    String author = authorResult.getString("Author Name");
                    int numberOfBooks = authorResult.getInt("NumberOfBooks");
                    mv_PieChart.getData().add(new PieChart.Data(author, (double) numberOfBooks / totalBooks * 100));
                }
                Book.initLabels(getNumBooks(), getNumMembers(), getNumBorrowedBooks());
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }

    @FXML
    ToggleButton viewPasscodeTB;

    @FXML
    private void handleViewPasscode(ActionEvent event) {
        if (viewPasscodeTB.isSelected()) {
            av_Passcode.setText(Member.getPasscode());
        } else {
            av_Passcode.setText("*********");
        }
    }

    @FXML
    TextField changeUsername;
    @FXML
    TextField changePasscode;

    public String getChangeUsername() {
        return changeUsername.getText();
    }

    public String getChangePasscode() {
        return changePasscode.getText();
    }

    @FXML
    private void handleChangeUsername(ActionEvent event) {
        Member.changeUsername(getChangeUsername(), changeUsername);
        mv_Username.setText(Member.getUsername());
        Member.initUserView(getAv_Username());
    }

    @FXML
    private void handleChangePasscode(ActionEvent event) {
        Member.changePasscode(getChangePasscode(), changePasscode);
    }
}
