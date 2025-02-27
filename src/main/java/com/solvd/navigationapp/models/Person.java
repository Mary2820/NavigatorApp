package com.solvd.navigationapp.models;

import com.solvd.navigationapp.enums.UserType;

public abstract class Person {
    private Long id;
    private String firstName;
    private String lastName;
    protected UserType userType;

    protected Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType()
    {
        return userType;
    }

}
