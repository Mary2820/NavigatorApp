package com.solvd.navigationapp.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User extends Person {
    private Long id;
    private String email;
    private String password;
    public User() {
        super();
    }

    public User(Long id, Long personId, String firstName, String lastName, String email, String password) {
        super(personId, firstName, lastName);
        this.id = id;
        this.email = email;
        this.password = password;
    }
    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
