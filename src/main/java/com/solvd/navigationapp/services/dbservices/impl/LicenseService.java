package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.daos.ILicenseDAO;
import com.solvd.navigationapp.models.License;
import com.solvd.navigationapp.services.dbservices.ILicenseService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LicenseService extends AbstractService<License> implements ILicenseService {
    private static final Logger logger = LogManager.getLogger(LicenseService.class.getName());
    private final ILicenseDAO licenseDAO;

    public LicenseService() {
        this.licenseDAO = DAOFactory.getInstance().getLicenseDAO();
    }

    @Override
    public Optional<License> getById(Long id) {
        try {
            logger.info("Getting license by id: {}", id);
            return Optional.ofNullable(licenseDAO.getById(id));
        } catch (Exception e) {
            logger.error("Error getting license by id {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean save(License entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid license data for saving");
            throw new IllegalArgumentException("Invalid license data");
        }
        try {
            Optional<License> existing = Optional.ofNullable(licenseDAO.getByNumber(entity.getNumber()));
            if (existing.isPresent()) {
                logger.error("License with number '{}' already exists", entity.getNumber());
                throw new IllegalArgumentException("License with this number already exists");
            }

            logger.info("Saving license: {}", entity);
            licenseDAO.insert(entity);
            return licenseDAO.getByNumber(entity.getNumber()) != null;
        } catch (Exception e) {
            logger.error("Error saving license {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(License entity) {
        if (!isValidData(entity) || entity.getId() == null) {
            logger.error("Invalid license data for updating");
            throw new IllegalArgumentException("Invalid license data");
        }
        try {
            if (licenseDAO.getById(entity.getId()) == null) {
                logger.error("License with id {} does not exist", entity.getId());
                throw new IllegalArgumentException("License does not exist");
            }

            logger.info("Updating license: {}", entity);
            licenseDAO.update(entity);

            License updated = licenseDAO.getById(entity.getId());
            return updated != null && updated.equals(entity);
        } catch (Exception e) {
            logger.error("Error updating license {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            if (licenseDAO.getById(id) == null) {
                logger.error("License with id {} does not exist", id);
                throw new IllegalArgumentException("License does not exist");
            }

            logger.info("Deleting license with id: {}", id);
            licenseDAO.deleteById(id);
            return licenseDAO.getById(id) == null;
        } catch (Exception e) {
            logger.error("Error deleting license with id {}: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<License> getByNumber(String number) {
        try {
            logger.info("Getting license by number: {}", number);
            return Optional.ofNullable(licenseDAO.getByNumber(number));
        } catch (Exception e) {
            logger.error("Error getting license by number {}: {}", number, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<License> getExpiredLicenses(LocalDate currentDate) {
        try {
            logger.info("Getting expired licenses as of: {}", currentDate);
            return licenseDAO.getExpiredLicenses(currentDate);
        } catch (Exception e) {
            logger.error("Error getting expired licenses: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<License> getValidLicenses(LocalDate currentDate) {
        try {
            logger.info("Getting valid licenses as of: {}", currentDate);
            return licenseDAO.getValidLicenses(currentDate);
        } catch (Exception e) {
            logger.error("Error getting valid licenses: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<License> getAll() {
        try {
            logger.info("Getting all licenses");
            return licenseDAO.getAll();
        } catch (Exception e) {
            logger.error("Error getting all licenses: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    protected boolean isValidData(License entity) {
        return entity != null &&
                entity.getNumber() != null && !entity.getNumber().isEmpty() &&
                entity.getExpirationDate() != null &&
                entity.getExpirationDate().isAfter(LocalDate.now());
    }
}
