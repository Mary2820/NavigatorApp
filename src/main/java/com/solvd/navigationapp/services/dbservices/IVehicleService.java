package com.solvd.navigationapp.services.dbservices;

import java.util.List;


import com.solvd.navigationapp.models.Vehicle;

public interface IVehicleService extends IService<Vehicle> {
    Vehicle getByRegistrationNumber(String registrationNumber);

    List<Vehicle> getByVehicleTypeId(Long vehicleTypeId);

    List<Vehicle> getByDriverId(Long driverId);

    List<Vehicle> getAllVehicles();
    
    boolean deleteByRegistrationNumber(String registrationNumber);
}

