package com.solvd.navigationapp.services.dbservices;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Vehicle;

public interface IVehicleService extends IService<Vehicle> {
    Optional<Vehicle> getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Long driverId);

    List<Vehicle> getAllVehicles();
    
    boolean deleteByRegistrationNumber(String registrationNumber);
}

