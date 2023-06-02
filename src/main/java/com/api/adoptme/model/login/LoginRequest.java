package com.api.adoptme.model.login;

public class LoginRequest {

    private String email;

    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    /**
     * Getter for the email field
     *
     * @return the email field value
     */
    public String getEmail() {return email;}
    /**
     * Getter for the password field
     *
     * @return the password field value
     */
    public String getPassword() {return password;}
}
