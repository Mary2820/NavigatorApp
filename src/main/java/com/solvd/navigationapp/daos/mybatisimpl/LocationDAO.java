package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.ILocationDAO;
import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Location;

import java.util.List;

public class LocationDAO extends AbstractMyBatisDAO<ILocationDAO> implements ILocationDAO {
    @Override
    public List<Location> getByCityId(Long cityId) {
        return executeInSession(mapper -> mapper.getByCityId(cityId));
    }

    @Override
    public List<Location> getByName(String name) {
        return executeInSession(mapper -> mapper.getByName(name));
    }

    @Override
    public List<Location> getByAddress(String address) {
        return executeInSession(mapper -> mapper.getByAddress(address));
    }

    @Override
    public List<Location> getByType(LocationType type) {
        return executeInSession(mapper -> mapper.getByType(type));
    }

    @Override
    public List<Location> getAll() {
        return executeInSession(mapper -> mapper.getAll());
    }

    @Override
    public void insert(Location entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Location getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(Location entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void deleteById(Long id) {
        executeInSessionVoid(mapper -> mapper.deleteById(id));
    }

    @Override
    protected Class<ILocationDAO> getMapperClass() {
        return ILocationDAO.class;
    }
}