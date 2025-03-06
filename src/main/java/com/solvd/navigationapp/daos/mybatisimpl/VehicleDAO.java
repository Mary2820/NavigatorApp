package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.IVehicleDAO;
import com.solvd.navigationapp.models.Vehicle;

import java.util.List;
import java.util.Optional;

public class VehicleDAO extends AbstractMyBatisDAO<IVehicleDAO> implements IVehicleDAO {
    @Override
    public Optional<Vehicle> getByRegistrationNumber(String registrationNumber) {
        return executeInSession(mapper -> mapper.getByRegistrationNumber(registrationNumber));
    }

    @Override
    public List<Vehicle> getByVehicleTypeId(Long vehicleTypeId) {
        return executeInSession(mapper -> mapper.getByVehicleTypeId(vehicleTypeId));
    }

    @Override
    public List<Vehicle> getByDriverId(Long driverId) {
        return executeInSession(mapper -> mapper.getByDriverId(driverId));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return executeInSession(IVehicleDAO::getAllVehicles);
    }

    @Override
    public void deleteByRegistrationNumber(String registrationNumber) {
        executeInSessionVoid(mapper -> mapper.deleteByRegistrationNumber(registrationNumber));
    }

    @Override
    public void insert(Vehicle entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<Vehicle> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(Vehicle entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void deleteById(Long id) {
        executeInSessionVoid(mapper -> mapper.deleteById(id));
    }

    @Override
    public boolean isRegistrationNumberTaken(String registrationNumber) {
        return executeInSession(mapper -> mapper.isRegistrationNumberTaken(registrationNumber));

    }

    @Override
    protected Class<IVehicleDAO> getMapperClass() {
        return IVehicleDAO.class;
    }

}