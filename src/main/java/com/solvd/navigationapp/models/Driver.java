package com.solvd.navigationapp.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.solvd.navigationapp.enums.UserType;

@XmlRootElement(name = "driver")
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver extends User {

    public Driver() {
        super();
        this.userType = UserType.DRIVER;
    }

    public Driver(Long id, String firstName, String lastName, String phoneNumber) {
        super(id, firstName, lastName, phoneNumber);
        this.userType = UserType.DRIVER;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "userType=" + userType +
                ", " + super.toString() +
                '}';
    }
}
