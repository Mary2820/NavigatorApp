package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.Vehicle;

import java.util.List;

public interface IVehicleDAO extends IDAO<Vehicle> {
    Vehicle getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Integer driverId);

    List<Vehicle> getBySeatsCountGreaterThan(Integer seatsCount);

    List<Vehicle> getBySeatsCountLessThan(Integer seatsCount);

    void deleteByRegistrationNumber(String registrationNumber);

    List<Vehicle> getAllVehicles();
}