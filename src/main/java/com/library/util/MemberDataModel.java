package com.library.util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemberDataModel {
    private final StringProperty memberID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty username;

    public MemberDataModel(String memberID, String firstName, String lastName, String username) {
        this.memberID = new SimpleStringProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.username = new SimpleStringProperty(username);
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

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty FirstNameProperty() {
        return firstName;
    }
    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    public StringProperty LastNameProperty() {
        return lastName;
    }
    public String getUsername() {
        return username.get();
    }
    public void setUsername(String username) {
        this.username.set(username);
    }
    public StringProperty UsernameProperty() {
        return username;
    }
}
