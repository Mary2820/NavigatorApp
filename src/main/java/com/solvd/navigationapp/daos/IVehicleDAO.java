package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Vehicle;

import java.util.List;

public interface IVehicleDAO extends IDAO<Vehicle> {
    Vehicle getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Long driverId);

    List<Vehicle> getAllVehicles();

    /*List<Vehicle> getBySeatsCountGreaterThan(Integer seatsCount);

    List<Vehicle> getBySeatsCountLessThan(Integer seatsCount);*/

    void deleteByRegistrationNumber(String registrationNumber);
}