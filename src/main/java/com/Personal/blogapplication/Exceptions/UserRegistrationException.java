package com.Personal.blogapplication.Exceptions;

public class UserRegistrationException extends Exception {

    // Default constructor
    public UserRegistrationException() {
        super();
    }

    // Constructor with a custom message
    public UserRegistrationException(String message) {
        super(message);
    }

    // Constructor with a custom message and a cause
    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a cause
    public UserRegistrationException(Throwable cause) {
        super(cause);
    }
}