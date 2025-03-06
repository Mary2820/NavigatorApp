package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.ILicenseDAO;
import com.solvd.navigationapp.models.License;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LicenseDAO extends AbstractMyBatisDAO<ILicenseDAO> implements ILicenseDAO {
    @Override
    public License getByNumber(String number) {
        return executeInSession(mapper -> mapper.getByNumber(number));
    }

    @Override
    public List<License> getExpiredLicenses(LocalDate currentDate) {
        return executeInSession(mapper -> mapper.getExpiredLicenses(currentDate));
    }

    @Override
    public List<License> getValidLicenses(LocalDate currentDate) {
        return executeInSession(mapper -> mapper.getValidLicenses(currentDate));
    }

    @Override
    public void insert(License entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<License> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(License entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void deleteById(Long id) {
        executeInSessionVoid(mapper -> mapper.deleteById(id));
    }

    @Override
    public List<License> getAll() {
        return executeInSession(ILicenseDAO::getAll);
    }
    @Override
    protected Class<ILicenseDAO> getMapperClass() {
        return ILicenseDAO.class;
    }
} 