package com.solvd.navigationapp.models;

public class Driver extends Person {
    private Long id;
    private Long licenseId;

    public Driver(Long id, Long personId, String firstName, String lastName, Long licenseId) {
        super(personId, firstName, lastName);
        this.id = id;
        this.licenseId = licenseId;
    }

    public Driver(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
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
