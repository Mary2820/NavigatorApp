package com.solvd.navigationapp.models;

public class User extends Person {
    private Long userId;  // id from Users table
    private String email;
    private String password;

    public User(Long userId, Long personId, String firstName, String lastName, String email, String password) {
        super(personId, firstName, lastName);
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
