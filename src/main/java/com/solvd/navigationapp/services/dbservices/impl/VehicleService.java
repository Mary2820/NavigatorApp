package com.solvd.navigationapp.services.dbservices.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.navigationapp.daos.IVehicleDAO;
import com.solvd.navigationapp.models.Vehicle;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.utils.DAOFactory;

public class VehicleService extends AbstractService<Vehicle> implements IVehicleService {
    private static final Logger logger = LogManager.getLogger(VehicleService.class.getName());
    private final IVehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = DAOFactory.getInstance().getVehicleDAO();
    }

    @Override
    public Vehicle getById(Long id) {
        return vehicleDAO.getById(id).orElse(null);
    }

    @Override
    public boolean save(Vehicle vehicle) {
        String registrationNumber = vehicle.getRegistrationNumber();
        if (!isValidData(vehicle)) {
            return false;
        }
        if (isRegistrationNumberTaken(registrationNumber)) {
            logger.warn("Registration number already taken: {}", registrationNumber);
            return false;
        }
        vehicleDAO.insert(vehicle);
        return true;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = vehicleDAO.getById(vehicle.getId());
        if (isValidData(vehicle) && existingVehicle.isPresent()) {
            vehicleDAO.update(vehicle);
            return true;
        }
        logger.error("Vehicle with ID {} not found", vehicle.getId());
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (vehicleDAO.getById(id).isPresent()) {
            vehicleDAO.deleteById(id);
            return true;
        }
        logger.error("Attempt to delete non-existent vehicle with ID: {}", id);
        return false;
    }

    @Override
    public Vehicle getByRegistrationNumber(String registrationNumber) {
        return vehicleDAO.getByRegistrationNumber(registrationNumber).get();
    }

    @Override
    public List<Vehicle> getByVehicleTypeId(Long vehicleTypeId) {
        List<Vehicle> vehicles = vehicleDAO.getByVehicleTypeId(vehicleTypeId);
        if (vehicles.isEmpty()) {
            logger.error("Vehicle not found by vehicle type id: {}", vehicleTypeId);
        }
        return vehicles;

    }

    @Override
    public List<Vehicle> getByDriverId(Long driverId) {
        List<Vehicle> vehicles = vehicleDAO.getByDriverId(driverId);
        if (vehicles.isEmpty()) {
            logger.error("Vehicle not found by driver id: {}", driverId);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = vehicleDAO.getAllVehicles();
        if (vehicles.isEmpty()) {
            logger.error("No vehicles found");
        }
        return vehicles;
    }

    @Override
    public boolean deleteByRegistrationNumber(String registrationNumber) {
        if (vehicleDAO.getByRegistrationNumber(registrationNumber).isPresent()) {
            vehicleDAO.deleteByRegistrationNumber(registrationNumber);
            return true; 
        }
        logger.warn("Attempt to delete non-existent vehicle with registration number: {}", registrationNumber);
        return false;
    }

    @Override
    protected boolean isValidData(Vehicle vehicle) {
        Long driverId = vehicle.getDriverId();
        String registrationNumber = vehicle.getRegistrationNumber();
        Integer seatsCount = vehicle.getSeatsCount();
        Long vehicleTypeId = vehicle.getVehicleTypeId();
        if (driverId == null ||
                registrationNumber == null || registrationNumber.trim().isEmpty() ||
                seatsCount == null || vehicleTypeId == null) {
            logger.warn("Missing required client fields");
            return false;
        }

        return true;
    }

    private boolean isRegistrationNumberTaken(String registrationNumber) {
            return vehicleDAO.isRegistrationNumberTaken(registrationNumber);    
        }
}
