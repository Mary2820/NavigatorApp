package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.daos.ICityDAO;
import com.solvd.navigationapp.models.City;
import com.solvd.navigationapp.services.dbservices.ICityService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CityService extends AbstractService<City> implements ICityService {
    private static final Logger logger = LogManager.getLogger(CityService.class.getName());
    private final ICityDAO cityDAO;

    public CityService() {
        this.cityDAO = DAOFactory.getInstance().getCityDAO();
    }

    @Override
    public Optional<City> getById(Long id) {
        try {
            City city = cityDAO.getById(id);
            return Optional.ofNullable(city);
        } catch (Exception e) {
            logger.error("Error in finding city by ID: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean save(City city) {
        if (!isValidData(city)) {
            return false;
        }
        try {
            cityDAO.insert(city);
            logger.info("City has been successfully added: {}", city.getId());
            return true;
        } catch (Exception e) {
            logger.error("Error in saving city: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(City city) {
        Optional<City> existingCity = getById(city.getId());
        if (existingCity == null) {
            logger.warn("City with the ID {} was not found", city.getId());
            return false;
        }
        try {
            cityDAO.update(city);
            logger.info("City has been successfully updated: {}", city.getId());
            return true;
        } catch (Exception e) {
            logger.error("Error in updating city: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<City> cityToDelete = getById(id);
        if (cityToDelete.isEmpty()) {
            logger.warn("Attempt to delete non-existent city with ID: {}", id);
            return false;
        }

        try {
            cityDAO.deleteById(id);
            logger.info("City has been successfully deleted: {}", id);
            return true;
        } catch (Exception e) {
            logger.error("Error in deleting city: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<City> getByName(String name) {
        try {
            return cityDAO.getByName(name);
        } catch (Exception e) {
            logger.error("Error in finding city by name: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<City> getByRegion(String region) {
        try {
            return cityDAO.getByRegion(region);
        } catch (Exception e) {
            logger.error("Error in finding city by region: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<City> getByCountry(String country) {
        try {
            return cityDAO.getByCountry(country);
        } catch (Exception e) {
            logger.error("Error in finding city by country: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    protected boolean isValidData(City city) {
        String cityName = city.getName();
        String region = city.getRegion();
        String country = city.getCountry();

        if (cityName == null || cityName.trim().isEmpty() ||
                region == null || region.trim().isEmpty() ||
                country == null || country.trim().isEmpty()) {
            logger.warn("Missing required city info");
            return false;
        }
        return true;
    }
}