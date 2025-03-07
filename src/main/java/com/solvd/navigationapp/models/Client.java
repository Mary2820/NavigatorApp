package com.solvd.navigationapp.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.solvd.navigationapp.enums.UserType;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends User {

    @XmlElement(name = "email")
    private String email;

    public Client() {
        super();
        this.userType = UserType.CLIENT;
    }

    public Client(Long id, String firstName, String lastName, String phoneNumber, String email) {
        super(id, firstName, lastName, phoneNumber);
        this.email = email;
        this.userType = UserType.CLIENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", userType=" + userType +
                ", " + super.toString() +
                '}';
    }
}
