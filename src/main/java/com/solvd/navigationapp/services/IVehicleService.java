package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Vehicle;

public interface IVehicleService extends IService<Vehicle> {
    Vehicle getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Integer driverId);

    List<Vehicle> getAllVehicles();

    void deleteByRegistrationNumber(String registrationNumber);
}
