package com.intellect37.api_test.model;

public class HelloModel {
    private String message;

    // Default constructor
    public HelloModel() {}

    // Constructor with message parameter
    public HelloModel(String message) {
        this.message = message;
    }

    // Getter for the message field
    public String getMessage() {
        return message;
    }

    // Setter for the message field
    public void setMessage(String message) {
        this.message = message;
    }
}
