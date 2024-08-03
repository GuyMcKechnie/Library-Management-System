package com.library.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookDataModel {
    private final StringProperty bookID;
    private final StringProperty title;
    private final StringProperty isbn;
    private final StringProperty authorID;

    public BookDataModel(String bookID, String title, String isbn, String authorID) {
        this.bookID = new SimpleStringProperty(bookID);
        this.title = new SimpleStringProperty(title);
        this.isbn = new SimpleStringProperty(isbn);
        this.authorID = new SimpleStringProperty(authorID);
    }

    public String getBookID() {
        return bookID.get();
    }

    public void setBookID(String bookID) {
        this.bookID.set(bookID);
    }

    public StringProperty bookIDProperty() {
        return bookID;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getISBN() {
        return isbn.get();
    }

    public void setISBN(String isbn) {
        this.isbn.set(isbn);
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public String getAuthorID() {
        return authorID.get();
    }

    public void setAuthorID(String authorID) {
        this.authorID.set(authorID);
    }

    public StringProperty authorIDProperty() {
        return authorID;
    }
}
