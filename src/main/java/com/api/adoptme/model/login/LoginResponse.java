package com.api.adoptme.model.login;

public class LoginResponse {
    private String message;
    /**
     * Constructor for LoginResponse
     *
     * @param message the message to set for the response object
     */
    public LoginResponse(String message) {
        this.message = message;
    }
    /**
     * Getter for the message field
     *
     * @return the message field value
     */
    public String getMessage() {
        return message;
    }
    /**
     * Setter for the message field
     *
     * @param message the message to set for the response object
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
