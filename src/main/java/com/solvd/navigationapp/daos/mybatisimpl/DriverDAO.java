package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.IDriverDAO;
import com.solvd.navigationapp.models.Driver;

import java.util.List;
import java.util.Optional;

public class DriverDAO extends AbstractMyBatisDAO<IDriverDAO> implements IDriverDAO {
 
    @Override
    public List<Driver> getByFullName(String firstName, String lastName) {
        return executeInSession(mapper -> mapper.getByFullName(firstName, lastName));
    }

    @Override
    public void insert(Driver entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<Driver> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(Driver entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void deleteById(Long id) {
        executeInSessionVoid(mapper -> mapper.deleteById(id));
    }

    @Override
    protected Class<IDriverDAO> getMapperClass() {
        return IDriverDAO.class;
    }
} 