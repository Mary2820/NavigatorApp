package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.ICityDAO;
import com.solvd.navigationapp.models.City;

import java.util.List;
import java.util.Optional;

public class CityDAO extends AbstractMyBatisDAO<ICityDAO> implements ICityDAO {
    @Override
    public List<City> getByName(String name) {
        return executeInSession(mapper -> mapper.getByName(name));
    }

    @Override
    public List<City> getByRegion(String region) {
        return executeInSession(mapper -> mapper.getByRegion(region));
    }

    @Override
    public List<City> getByCountry(String country) {
        return executeInSession(mapper -> mapper.getByCountry(country));
    }

    @Override
    public void insert(City entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<City> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(City entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void deleteById(Long id) {
        executeInSessionVoid(mapper -> mapper.deleteById(id));
    }

    @Override
    protected Class<ICityDAO> getMapperClass() {
        return ICityDAO.class;
    }
}
