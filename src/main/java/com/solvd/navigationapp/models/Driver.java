package com.solvd.navigationapp.models;

import com.solvd.navigationapp.enums.UserType;

public class Driver extends User {
    private Long id;
    private Long licenseId;

    public Driver(Long id, Long personId, String firstName, String lastName, Long licenseId) {
        super(personId, firstName, lastName);
        this.id = id;
        this.licenseId = licenseId;
        this.userType = UserType.DRIVER;
    
    }

    public Driver(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.userType = UserType.DRIVER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }

}
