package com.solvd.navigationapp.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.solvd.navigationapp.enums.UserType;

@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client extends User {


    public Client() {
        super();
        this.userType = UserType.CLIENT;
    }

    public Client(Long id, String firstName, String lastName, String email, String phoneNumber) {
        super(id, firstName, lastName, email, phoneNumber);
        this.userType = UserType.CLIENT;
    }


    @Override
    public String toString() {
        return "Client{" +
                "userType=" + userType +
                ", " + super.toString() +
                '}';
    }

}
