package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.models.Driver;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.navigationapp.daos.IDriverDAO;
import com.solvd.navigationapp.services.dbservices.IDriverService;
import com.solvd.navigationapp.utils.DAOFactory;

public class DriverService extends AbstractService<Driver> implements IDriverService {
    private static final Logger logger = LogManager.getLogger(DriverService.class.getName());
    private final IDriverDAO driverDAO;

    public DriverService() {
        this.driverDAO = DAOFactory.getInstance().getDriverDAO();
    }

    @Override
    public Driver getById(Long id) {
        return driverDAO.getById(id).get();
    }

    @Override
    public boolean save(Driver driver) {
        if (!isValidData(driver)) {
            return false;
        }

        driverDAO.insert(driver);
        return true;
    }

    @Override
    public boolean update(Driver driver) {
        Optional<Driver> existingDriver = driverDAO.getById(driver.getId());
        if (isValidData(driver) && existingDriver.isPresent()) {
            driverDAO.update(driver);
            return true;
        }
        logger.error("Error updating driver: {}", driver.getId());
        return false;

    }

    @Override
    public boolean deleteById(Long id) {
        if (driverDAO.getById(id).isPresent()) {
            driverDAO.deleteById(id);
            return true;
        }
        logger.error("Error deleting driver  {}", id);
        return false;
    }

    @Override
    public List<Driver> getByFullName(String firstName, String lastName) {
        List<Driver> driver = driverDAO.getByFullName(firstName, lastName);
        if (driver.isEmpty()) {
            logger.error("Driver with name {} {} not found", firstName, lastName);
        }
        return driver;
    }

    @Override
    protected boolean isValidData(Driver driver) {
        if (driver.getFirstName() == null || driver.getFirstName().trim().isEmpty() ||
                driver.getLastName() == null || driver.getLastName().trim().isEmpty() ||
                driver.getPhoneNumber() == null || driver.getPhoneNumber().trim().isEmpty()) {
            logger.warn("Missing required client fields");
            return false;
        }
        return true;
    }

}
