package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface IVehicleDAO extends IDAO<Vehicle> {
    Optional<Vehicle> getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Long driverId);

    List<Vehicle> getAllVehicles();

    void deleteByRegistrationNumber(String registrationNumber);

    boolean isRegistrationNumberTaken(String registrationNumber);
}