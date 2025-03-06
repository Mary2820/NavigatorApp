package com.solvd.navigationapp.services.dbservices.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Optional<Location> getById(Long id) {
        try {
            return Optional.ofNullable(locationDAO.getById(id));
        } catch (Exception e) {
            logger.error("Error getting location by id {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean save(Location entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid location data for saving");
            throw new IllegalArgumentException("Invalid location data");
        }

        try {
            List<Location> existingLocations = locationDAO.getByCityId(entity.getCityId());
            for (Location existing : existingLocations) {
                if (existing.getName().equalsIgnoreCase(entity.getName())) {
                    logger.error("Location with name '{}' already exists in city with id {}", 
                        entity.getName(), entity.getCityId());
                    throw new IllegalArgumentException("Location with this name already exists in this city");
                }
            }
            logger.info("Saving location: {}", entity);
            locationDAO.insert(entity);

            List<Location> locations = locationDAO.getByCityId(entity.getCityId());
            for (Location saved : locations) {
                if (saved.getName().equalsIgnoreCase(entity.getName()) && 
                    saved.getAddress().equals(entity.getAddress())) {
                    return true;
                }
            }
            logger.error("Failed to verify save operation for location: {}", entity);
            return false;
        } catch (Exception e) {
            logger.error("Error saving location {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Location entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid location data for updating");
            throw new IllegalArgumentException("Invalid location data");
        }
        try {
            Location existingLocation = locationDAO.getById(entity.getId());
            if (existingLocation == null) {
                logger.error("Location with id {} does not exist", entity.getId());
                throw new IllegalArgumentException("Location does not exist");
            }
            List<Location> existingLocations = locationDAO.getByCityId(entity.getCityId());
            for (Location existing : existingLocations) {
                if (existing.getName().equalsIgnoreCase(entity.getName()) && 
                    !existing.getId().equals(entity.getId())) {
                    logger.error("Location with name '{}' already exists in city with id {}", 
                        entity.getName(), entity.getCityId());
                    throw new IllegalArgumentException("Location with this name already exists in this city");
                }
            }
            logger.info("Updating location: {}", entity);
            locationDAO.update(entity);

            Location updated = locationDAO.getById(entity.getId());
            if (updated != null && 
                updated.getName().equals(entity.getName()) &&
                updated.getAddress().equals(entity.getAddress()) &&
                updated.getType().equals(entity.getType()) &&
                updated.getCityId().equals(entity.getCityId())) {
                return true;
            }
            logger.error("Failed to verify update operation for location: {}", entity);
            return false;
        } catch (Exception e) {
            logger.error("Error updating location {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Location location = locationDAO.getById(id);
            if (location == null) {
                logger.error("Location with id {} does not exist", id);
                throw new IllegalArgumentException("Location does not exist");
            }
            logger.info("Deleting location: {}", location);
            locationDAO.deleteById(id);

            Location deleted = locationDAO.getById(id);
            return deleted == null;
        } catch (Exception e) {
            logger.error("Error deleting location with id {}: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Location> getByCityId(Long cityId) {
        try {
            logger.info("Getting locations by city id: {}", cityId);
            return locationDAO.getByCityId(cityId);
        } catch (Exception e) {
            logger.error("Error getting locations by city id {}: {}", cityId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Location> getByName(String name) {
        try {
            return locationDAO.getByName(name);
        } catch (Exception e) {
            logger.error("Error getting locations by name {}: {}", name, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Location> getByAddress(String address) {
        try {
            logger.info("Getting locations by address: {}", address);
            return locationDAO.getByAddress(address);
        } catch (Exception e) {
            logger.error("Error getting locations by address {}: {}", address, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Location> getByType(LocationType type) {
        try {
            logger.info("Getting locations by type: {}", type);
            return locationDAO.getByType(type);
        } catch (Exception e) {
            logger.error("Error getting locations by type {}: {}", type, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Location> getAll() {
        try {
            logger.info("Getting all locations");
            return locationDAO.getAll();
        } catch (Exception e) {
            logger.error("Error getting all locations: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    protected boolean isValidData(Location entity) {
        return entity.getName() != null && !entity.getName().trim().isEmpty() &&
               entity.getAddress() != null && !entity.getAddress().trim().isEmpty() &&
               entity.getType() != null &&
               entity.getCityId() != null && entity.getCityId() > 0;
    }
}
