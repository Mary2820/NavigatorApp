package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.daos.ICityDAO;
import com.solvd.navigationapp.models.City;
import com.solvd.navigationapp.services.dbservices.ICityService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CityService extends AbstractService<City> implements ICityService {
    private static final Logger logger = LogManager.getLogger(CityService.class.getName());
    private final ICityDAO cityDAO;

    public CityService() {
        this.cityDAO = DAOFactory.getInstance().getCityDAO();
    }

    @Override
    public City getById(Long id) {
        return cityDAO.getById(id).get();
    }

    @Override
    public boolean save(City city) {
        if (!isValidData(city)) {
            return false;
        }
        cityDAO.insert(city);
        return true;
    }

    @Override
    public boolean update(City city) {
        Optional<City> existingCity = cityDAO.getById(city.getId());
        if (isValidData(city) && existingCity.isPresent()) {
            cityDAO.update(city);
            return true;
        }
        logger.error("Error in updating city: {}", city.getId());
        return false;
    }


    @Override
    public boolean deleteById(Long id) {
        if (cityDAO.getById(id).isPresent()) {
            cityDAO.deleteById(id);
            return true;
        }
        logger.error("Error deleting client: {}", id);
        return false;
    }

    @Override
    public List<City> getByName(String name) {
        List<City> cities = cityDAO.getByName(name);
        if (cities.isEmpty()) {
            logger.error("City not found by name: {}", name);
        }
        return cities;
    }

    @Override
    public List<City> getByRegion(String region) {
        List<City> cities = cityDAO.getByRegion(region);
        if (cities.isEmpty()) {
            logger.error("City not found by region: {}", region);
        }
        return cities;
    }

    @Override
    public List<City> getByCountry(String country) {
        List<City> cities = cityDAO.getByCountry(country);
        if (cities.isEmpty()) {
            logger.error("City not found by country: {}", country);
        }
        return cities;
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
