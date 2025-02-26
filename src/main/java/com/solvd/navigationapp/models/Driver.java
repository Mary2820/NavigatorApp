package com.solvd.navigationapp.models;

public class Driver extends Person {
    private Long driverId;  // id from Drivers table
    private Long licenseId;

    public Driver(Long driverId, Long personId, String firstName, String lastName, Long licenseId) {
        super(personId, firstName, lastName);
        this.driverId = driverId;
        this.licenseId = licenseId;
    }

    public Driver(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Long licenseId) {
        this.licenseId = licenseId;
    }
}
