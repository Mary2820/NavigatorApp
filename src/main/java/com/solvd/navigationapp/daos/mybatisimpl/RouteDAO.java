package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.IRouteDAO;
import com.solvd.navigationapp.models.Route;

import java.util.List;
import java.util.Optional;

public class RouteDAO extends AbstractMyBatisDAO<IRouteDAO> implements IRouteDAO {
    @Override
    public List<Route> getByStartPointId(Long startPointId) {
        return executeInSession(mapper -> mapper.getByStartPointId(startPointId));
    }

    @Override
    public List<Route> getByEndPointId(Long endPointId) {
        return executeInSession(mapper -> mapper.getByEndPointId(endPointId));
    }

    @Override
    public List<Route> getByVehicleId(Long vehicleId) {
        return executeInSession(mapper -> mapper.getByVehicleId(vehicleId));
    }

    @Override
    public List<Route> getByStartAndEndPoints(Long startPointId, Long endPointId) {
        return executeInSession(mapper -> mapper.getByStartAndEndPoints(startPointId, endPointId));
    }

    @Override
    public Route getByStartEndAndVehicle(Long startPointId, Long endPointId, Long vehicleId) {
        return executeInSession(mapper -> mapper.getByStartEndAndVehicle(startPointId, endPointId, vehicleId));
    }

    @Override
    public int countTotal() {
        return executeInSession(IRouteDAO::countTotal);
    }

    @Override
    public void insert(Route entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<Route> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(Route entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void delete(Long id) {
        executeInSessionVoid(mapper -> mapper.delete(id));
    }

    @Override
    protected Class<IRouteDAO> getMapperClass() {
        return IRouteDAO.class;
    }
} 