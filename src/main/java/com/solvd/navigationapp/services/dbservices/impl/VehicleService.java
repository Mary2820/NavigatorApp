package com.solvd.navigationapp.services.dbservices.impl;

import java.util.ArrayList;
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
    public Optional<Vehicle> getById(Long id) {
        try {
            return Optional.ofNullable(vehicleDAO.getById(id));
        } catch (Exception e) {
            logger.error("Error finding vehicle by ID: {}", e.getMessage());
            return Optional.empty();
        }
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
        try {
            vehicleDAO.insert(vehicle);
            logger.info("Vehicle successfully registered: {}", vehicle.getId());
            return true;
        } catch (Exception e) {
            logger.error("Error saving vehicle: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = getById(vehicle.getId());
        if (existingVehicle.isEmpty()) {
            logger.warn("Vehicle with ID {} not found", vehicle.getId());
            return false;
        }
        try {
            vehicleDAO.update(vehicle);
            logger.info("Vehicle successfully updated: {}", vehicle.getId());
            return true;
        } catch (Exception e) {
            logger.error("Error updating vehicle: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (getById(id).isEmpty()) {
            logger.warn("Attempt to delete non-existent vehicle with ID: {}", id);
            return false;
        }
        try {
            vehicleDAO.deleteById(id);
            logger.info("Vehicle successfully deleted: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting vehicle: {}", e.getMessage());
            return false;
        }
    }
    
    @Override
    public Optional<Vehicle> getByRegistrationNumber(String registrationNumber) {
        try {
            return Optional.ofNullable(vehicleDAO.getByRegistrationNumber(registrationNumber));
        } catch (Exception e) {
            logger.error("Error finding vehicle by registration number {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Vehicle> getByVehicleTypeId(Long vehicleTypeId) {
        try {
            return vehicleDAO.getByVehicleTypeId(vehicleTypeId);
        } catch (Exception e) {
            logger.error("Error finding vehicle by vehicle id: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Vehicle> getByDriverId(Long driverId) {
        try {
            return vehicleDAO.getByDriverId(driverId);
        } catch (Exception e) {
            logger.error("Error finding vehicle by driver id: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        try {
            return vehicleDAO.getAllVehicles();
        } catch (Exception e) {
            logger.error("Error getting all vehicles: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean deleteByRegistrationNumber(String registrationNumber) {
        if (getByRegistrationNumber(registrationNumber) == null) {
            logger.warn("Attempt to delete non-existent vehicle with registration number: {}", registrationNumber);
            return false;
        }

        try {
            vehicleDAO.deleteByRegistrationNumber(registrationNumber);
            logger.info("Vehicle successfully deleted by registartion number {}", registrationNumber);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting vehicle by registration number: {}", e.getMessage());
            return false;
        }
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
        try {
            return vehicleDAO.isRegistrationNumberTaken(registrationNumber);
        } catch (Exception e) {
            logger.error("Error checking if registration number is taken: {}", e.getMessage());
            return false;
        }

    }
}
