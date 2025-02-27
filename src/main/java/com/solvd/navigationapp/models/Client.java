package com.solvd.navigationapp.models;

import com.solvd.navigationapp.enums.UserType;

public class Client extends Person {
    private Long id;
    private String email;
    private String password;

    public Client(Long id, Long personId, String firstName, String lastName, String email, String password) {
        super(personId, firstName, lastName);
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = UserType.USER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
