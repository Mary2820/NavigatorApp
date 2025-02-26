package com.solvd.navigationapp.models;

public class Vehicle {
    private Long id;
    private Long vehicleTypeId;
    private String registrationNumber;
    private Integer seatsCount;
    private Integer driverId;

    public Vehicle(Long id, Long vehicleTypeId, String registrationNumber, Integer seatsCount, Integer driverId) {
        this.id = id;
        this.vehicleTypeId = vehicleTypeId;
        this.registrationNumber = registrationNumber;
        this.seatsCount = seatsCount;
        this.driverId = driverId;
    }

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(Integer seatsCount) {
        this.seatsCount = seatsCount;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
}
