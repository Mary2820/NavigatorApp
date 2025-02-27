package com.solvd.navigationapp.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import com.solvd.navigationapp.enums.UserType;

@XmlRootElement(name = "driver")
@XmlAccessorType(XmlAccessType.FIELD)
public class Driver extends User {
    
    @XmlElement(name = "licenseId")
    private Long licenseId;
    
    @XmlElement(name = "phoneNumber")
    private String phoneNumber;

    public Driver() {
        super();
        this.userType = UserType.DRIVER;
    }

    public Driver(Long id, String firstName, String lastName, String phoneNumber, Long licenseId) {
        super(id, firstName, lastName);
        this.phoneNumber = phoneNumber;
        this.licenseId = licenseId;
        this.userType = UserType.DRIVER;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
