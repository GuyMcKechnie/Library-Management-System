package com.library.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BorrowedBookDataModel {
    private final StringProperty bookID;
    private final StringProperty memberID;
    private final StringProperty borrowedDate;
    private final StringProperty returnDate;
    private final StringProperty borrowedID;

    public BorrowedBookDataModel(String bookID, String memberID, String borrowedDate, String returnDate, String borrowedID) {
        this.bookID = new SimpleStringProperty(bookID);
        this.memberID = new SimpleStringProperty(memberID);
        this.borrowedDate = new SimpleStringProperty(borrowedDate);
        this.returnDate = new SimpleStringProperty(returnDate);
        this.borrowedID = new SimpleStringProperty(borrowedID);
    }

    public String getBookID() {
        return bookID.get();
    }

    public void setBookID(String bookID) {
        this.bookID.set(bookID);
    }

    public StringProperty BookIDProperty() {
        return bookID;
    }

    public String getMemberID() {
        return memberID.get();
    }

    public void setMemberID(String memberID) {
        this.memberID.set(memberID);
    }

    public StringProperty MemberIDProperty() {
        return memberID;
    }

    public String getBorrowedDate() {
        return borrowedDate.get();
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate.set(borrowedDate);
    }

    public StringProperty BorrowedDateProperty() {
        return borrowedDate;
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    public StringProperty ReturnDateProperty() {
        return returnDate;
    }

    public String getBorrowedID() {
        return borrowedID.get();
    }

    public void setBorrowedID(String borrowedID) {
        this.borrowedID.set(borrowedID);
    }

    public StringProperty BorrowedIDProperty() {
        return borrowedID;
    }
}
