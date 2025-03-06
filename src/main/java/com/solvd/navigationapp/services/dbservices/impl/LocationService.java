package com.solvd.navigationapp.services.dbservices.impl;

import java.util.List;

import com.solvd.navigationapp.daos.ILocationDAO;
import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationService extends AbstractService<Location> implements ILocationService {
    private static final Logger logger = LogManager.getLogger(LocationService.class.getName());
    private final ILocationDAO locationDAO;

    public LocationService() {
        this.locationDAO = DAOFactory.getInstance().getLocationDAO();
    }

    @Override
    public Location getById(Long id) {
        return locationDAO.getById(id).get();
    }

    @Override
    public boolean save(Location entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid location data for saving");
            return false;
            // throw new IllegalArgumentException("Invalid location data");
        }
        List<Location> existingLocations = locationDAO.getByCityId(entity.getCityId());
        for (Location existing : existingLocations) {
            if (existing.getName().equalsIgnoreCase(entity.getName())) {
                logger.error("Location with name '{}' already exists in city with id {}",
                        entity.getName(), entity.getCityId());
                return false;
                // throw new IllegalArgumentException("Location with this name already exists in
                // this city");
            }
        }
        locationDAO.insert(entity);
        return true;

        /*
         * List<Location> locations = locationDAO.getByCityId(entity.getCityId());
         * for (Location saved : locations) {
         * if (saved.getName().equalsIgnoreCase(entity.getName()) &&
         * saved.getAddress().equals(entity.getAddress())) {
         * return true;
         * }
         * }
         * logger.error("Failed to verify save operation for location: {}", entity);
         * return false;
         */
    }

    @Override
    public boolean update(Location entity) {
        Location existingLocation = getById(entity.getId());
        if (!isValidData(entity) && existingLocation != null) {
            List<Location> existingLocations = locationDAO.getByCityId(entity.getCityId());
            for (Location existing : existingLocations) {
                if (existing.getName().equalsIgnoreCase(entity.getName()) &&
                        !existing.getId().equals(entity.getId())) {
                    logger.error("Location with name '{}' already exists in city with id {}",
                            entity.getName(), entity.getCityId());
                    return false;
                }
            }
            locationDAO.update(entity);
            return true;
            // throw new IllegalArgumentException("Invalid location data");
        }
        return false;
    }

    // throw new IllegalArgumentException("Location does not exist");

    // throw new IllegalArgumentException("Location with this name already exists in
    // this city");

    /*
     * Location updated = getById(entity.getId());
     * if (updated != null &&
     * updated.getName().equals(entity.getName()) &&
     * updated.getAddress().equals(entity.getAddress()) &&
     * updated.getType().equals(entity.getType()) &&
     * updated.getCityId().equals(entity.getCityId())) {
     * return true;
     * }
     * logger.error("Failed to verify update operation for location: {}", entity);
     * return false;
     * }
     */

    @Override
    public boolean deleteById(Long id) {
        if (locationDAO.getById(id).isPresent()) {
            locationDAO.deleteById(id);
            return true;
        }
        logger.error("Location with id {} does not exist", id);
        return false;
    }

    @Override
    public List<Location> getByCityId(Long cityId) {
        List<Location> locations = locationDAO.getByCityId(cityId);
        if (locations.isEmpty()) {
            logger.error("No locations found for city id {}", cityId);
        }
        return locations;
    }

    @Override
    public List<Location> getByName(String name) {
        List<Location> locations = locationDAO.getByName(name);
        if (locations.isEmpty()) {
            logger.error("No locations found for name {}", name);
        }
        return locations;
    }

    @Override
    public List<Location> getByAddress(String address) {
        List<Location> locations = locationDAO.getByAddress(address);
        if (locations.isEmpty()) {
            logger.error("No locations found for address {}", address);
        }
        return locations;
    }

    @Override
    public List<Location> getByType(LocationType type) {
        List<Location> locations = locationDAO.getByType(type);
        if (locations.isEmpty()) {
            logger.error("No locations found for type {}", type);
        }
        return locations;
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = locationDAO.getAll();
        if (locations.isEmpty()) {
            logger.error("No locations found");
        }
        return locations;
    }

    @Override
    protected boolean isValidData(Location entity) {
        return entity.getName() != null && !entity.getName().trim().isEmpty() &&
                entity.getAddress() != null && !entity.getAddress().trim().isEmpty() &&
                entity.getType() != null &&
                entity.getCityId() != null && entity.getCityId() > 0;
    }
}
