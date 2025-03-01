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

    @XmlElement(name = "phoneNumber")
    private String phoneNumber;

    public Client() {
        super();
        this.userType = UserType.CLIENT;
    }

    public Client(Long id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = UserType.CLIENT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                '}';
    }
}
