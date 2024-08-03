package com.library.model;

import com.library.util.BorrowedBookDataModel;
import com.library.util.BookDataModel;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.sql.*;

public class Book {
    public static void initPieChart(PieChart pieChart) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet totalBooksResult = statement.executeQuery("SELECT COUNT(BookID) AS TotalBooks FROM tblBooks");
            totalBooksResult.next();
            int totalBooks = totalBooksResult.getInt("TotalBooks");
            ResultSet authorResult = statement.executeQuery("SELECT a.[Author Name], COUNT(b.BookID) AS NumberOfBooks " +
                    "FROM tblBooks b " +
                    "INNER JOIN tblAuthors a ON b.AuthorID = a.[Author ID] " +
                    "GROUP BY a.[Author Name]");
            pieChart.getData().clear();
            while (authorResult.next()) {
                String author = authorResult.getString("Author Name");
                int numberOfBooks = authorResult.getInt("NumberOfBooks");
                pieChart.getData().add(new PieChart.Data(author, (double) numberOfBooks / totalBooks * 100));
            }
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void initLabels(Label numBooks, Label numMembers, Label numBorrowedBooks) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();

            ResultSet totalBooksResult = statement.executeQuery("SELECT COUNT(BookID) AS TotalBooks FROM tblBooks");
            totalBooksResult.next();
            int totalBooks = totalBooksResult.getInt("TotalBooks");
            numBooks.setText(String.valueOf(totalBooks));

            ResultSet totalMembersResult = statement.executeQuery("SELECT COUNT(MemberID) AS TotalMembers FROM tblMembers");
            totalMembersResult.next();
            int totalMembers = totalMembersResult.getInt("TotalMembers");
            numMembers.setText(String.valueOf(totalMembers));

            ResultSet totalBorrowedBooksResult = statement.executeQuery("SELECT COUNT(BorrowedID) AS TotalBorrowed FROM tblBorrowedBooks");
            totalBorrowedBooksResult.next();
            int totalBorrowedBooks = totalBorrowedBooksResult.getInt("TotalBorrowed");
            numBorrowedBooks.setText(String.valueOf(totalBorrowedBooks));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void initTable(@SuppressWarnings("rawtypes") TreeTableView bookTreeTable) {
        //Open the database and assign all the data in the table to cells in the treeTable
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblBooks");
            //Remove existing data
            bookTreeTable.getColumns().clear();
            //Add all the data from the table to the tree table
            TreeTableColumn<BookDataModel, String> IDColumn = new TreeTableColumn<>("ID");
            IDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("BookID"));
            TreeTableColumn<BookDataModel, String> titleColumn = new TreeTableColumn<>("Title");
            titleColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("Title"));
            TreeTableColumn<BookDataModel, String> isbnColumn = new TreeTableColumn<>("ISBN");
            isbnColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("ISBN"));
            TreeTableColumn<BookDataModel, String> authorIDColumn = new TreeTableColumn<>("Author ID");
            authorIDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("AuthorID"));
            bookTreeTable.getColumns().add(IDColumn);
            bookTreeTable.getColumns().add(titleColumn);
            bookTreeTable.getColumns().add(isbnColumn);
            bookTreeTable.getColumns().add(authorIDColumn);
            TreeItem<BookDataModel> rootItem = new TreeItem<>(new BookDataModel("IDRoot", "titleRoot", "isbnRoot", "usernameRoot"));
            rootItem.setExpanded(true);
            while (resultSet.next()) {
                String bookID = resultSet.getString("BookID");
                String title = resultSet.getString("Title");
                String isbn = resultSet.getString("ISBN");
                String author = resultSet.getString("AuthorID");
                TreeItem<BookDataModel> item = new TreeItem<>(new BookDataModel(bookID, title, isbn, author));
                rootItem.getChildren().add(item);
            }
            bookTreeTable.setRoot(rootItem);
            bookTreeTable.setShowRoot(false);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void initBorrowedBookTable(@SuppressWarnings("rawtypes") TreeTableView borrowedBookTreeTable) {
        //Open the database and aadd the data from the borrowed books table into the tree table
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection connection = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/com/library/database/LibDatabase.accdb");
            System.out.println("Database connected.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tblBorrowedBooks");
            //Remove existing data
            borrowedBookTreeTable.getColumns().clear();
            //Add all the data from the borrowed books table to the tree table
            TreeTableColumn<BorrowedBookDataModel, String> bookIDColumn = new TreeTableColumn<>("Book ID");
            bookIDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("BookID"));
            TreeTableColumn<BorrowedBookDataModel, String> memberIDColumn = new TreeTableColumn<>("Member ID");
            memberIDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("MemberID"));
            TreeTableColumn<BorrowedBookDataModel, String> borrowDateColumn = new TreeTableColumn<>("Borrowed Date");
            borrowDateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("BorrowedDate"));
            TreeTableColumn<BorrowedBookDataModel, String> returnDateColumn = new TreeTableColumn<>("Return Date");
            returnDateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("ReturnDate"));
            TreeTableColumn<BorrowedBookDataModel, String> borrowedIDColumn = new TreeTableColumn<>("Borrowed ID");
            borrowedIDColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("BorrowedID"));
            borrowedBookTreeTable.getColumns().add(borrowedIDColumn);
            borrowedBookTreeTable.getColumns().add(bookIDColumn);
            borrowedBookTreeTable.getColumns().add(memberIDColumn);
            borrowedBookTreeTable.getColumns().add(borrowDateColumn);
            borrowedBookTreeTable.getColumns().add(returnDateColumn);
            TreeItem<BorrowedBookDataModel> rootItem = new TreeItem<>(new BorrowedBookDataModel("bookIDRoot", "memberIDRoot", "borrowedDateRoot", "returnDateRoot", "borrowedIDRoot"));
            rootItem.setExpanded(true);
            while (resultSet.next()) {
                String bookID = resultSet.getString("BookID");
                String memberID = resultSet.getString("MemberID");
                String borrowedDate = resultSet.getString("BorrowedDate");
                String returnDate = resultSet.getString("ReturnDate");
                String borrowedID = resultSet.getString("BorrowedID");
                //@todo TRIM DATE
                TreeItem<BorrowedBookDataModel> item = new TreeItem<>(new BorrowedBookDataModel(bookID, memberID, borrowedDate, returnDate, borrowedID));
                rootItem.getChildren().add(item);
            }
            borrowedBookTreeTable.setRoot(rootItem);
            borrowedBookTreeTable.setShowRoot(false);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
