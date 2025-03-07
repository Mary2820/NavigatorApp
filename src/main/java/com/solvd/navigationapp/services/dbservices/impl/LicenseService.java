package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.daos.ILicenseDAO;
import com.solvd.navigationapp.models.License;
import com.solvd.navigationapp.services.dbservices.ILicenseService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LicenseService extends AbstractService<License> implements ILicenseService {
    private static final Logger logger = LogManager.getLogger(LicenseService.class);
    private final ILicenseDAO licenseDAO;

    public LicenseService() {
        this.licenseDAO = DAOFactory.getInstance().getLicenseDAO();
    }

    @Override
    public License getById(Long id) {
        return licenseDAO.getById(id).orElse(null);
    }

    @Override
    public boolean save(License entity) {
        if (!isValidData(entity) || licenseDAO.getByNumber(entity.getNumber()).isPresent()) {
            return false;
        }
        licenseDAO.insert(entity);
        return licenseDAO.getByNumber(entity.getNumber()).isPresent();
    }

    @Override
    public boolean update(License entity) {
        if (!isValidData(entity) || entity.getId() == null || licenseDAO.getById(entity.getId()).isEmpty()) {
            return false;
        }
        licenseDAO.update(entity);
        return licenseDAO.getById(entity.getId()).map(updated -> updated.equals(entity)).orElse(false);
    }

    @Override
    public boolean deleteById(Long id) {
        if (licenseDAO.getById(id).isEmpty()) {
            return false;
        }
        licenseDAO.deleteById(id);
        return licenseDAO.getById(id).isEmpty();
    }

    @Override
    public License getByNumber(String number) {
        return licenseDAO.getByNumber(number).get();
    }

    @Override
    public List<License> getExpiredLicenses(LocalDate currentDate) {
        return licenseDAO.getExpiredLicenses(currentDate);
    }

    @Override
    public List<License> getValidLicenses(LocalDate currentDate) {
        return licenseDAO.getValidLicenses(currentDate);
    }

    @Override
    public List<License> getAll() {
        return licenseDAO.getAll();
    }

    @Override
    protected boolean isValidData(License entity) {
        return entity != null &&
                entity.getNumber() != null && !entity.getNumber().isEmpty() &&
                entity.getExpirationDate() != null &&
                entity.getExpirationDate().isAfter(LocalDate.now());
    }
}
