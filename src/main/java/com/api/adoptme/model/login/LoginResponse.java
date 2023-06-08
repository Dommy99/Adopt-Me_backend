package com.api.adoptme.model.login;

public class LoginResponse {
    private String message;
    private Long userId; // add a new field for userId

    /**
     * Constructor for LoginResponse
     *
     * @param message the message to set for the response object
     * @param userId the user id to set for the response object
     */
    public LoginResponse(String message, Long userId) {
        this.message = message;
        this.userId = userId; // set the userId in constructor
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

    /**
     * Getter for the userId field
     *
     * @return the userId field value
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter for the userId field
     *
     * @param userId the userId to set for the response object
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
