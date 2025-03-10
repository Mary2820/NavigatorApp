package com.solvd.navigationapp.services.dbservices.impl;
import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.daos.IRouteDAO;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RouteService extends AbstractService<Route> implements IRouteService {
    private static final Logger logger = LogManager.getLogger(RouteService.class.getName());
    private final IRouteDAO routeDAO;

    public RouteService() {
        this.routeDAO = DAOFactory.getInstance().getRouteDAO();
    }

    @Override
    public Route getById(Long id) {
        return routeDAO.getById(id).get();
    }

    @Override
    public boolean save(Route entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid route data for saving");
            return false;
        }
        routeDAO.insert(entity);
        return true;
    }

    @Override
    public boolean update(Route entity) {
        Optional<Route> existingRoute = routeDAO.getById(entity.getId());
        if (isValidData(entity) && existingRoute.isPresent()) {
            routeDAO.update(entity);
            return true;
        }
        logger.error("Error updating route: {}", entity.getId());
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (routeDAO.getById(id).isPresent()) {
            routeDAO.deleteById(id);
            return true;
        }
        logger.error("Route with id {} does not exist", id);
        return false;

    }

    @Override
    public List<Route> getByStartPointId(Long startPointId) {
        List<Route> routes = routeDAO.getByStartPointId(startPointId);
        if(routes.isEmpty()){
            logger.error("No routes found for start point id {}", startPointId);
        }
        return routes;   
    }

    @Override
    public List<Route> getByEndPointId(Long endPointId) {
        List<Route> routes = routeDAO.getByEndPointId(endPointId);
        if(routes.isEmpty()){
            logger.error("No routes found for end point id {}", endPointId);
        }
        return routes;
    }

    @Override
    public List<Route> getByVehicleId(Long vehicleId) {
        List<Route> routes = routeDAO.getByVehicleId(vehicleId);
        if(routes.isEmpty()){
            logger.error("No routes found for vehicle id {}", vehicleId);
        }
        return routes;
    }

    @Override
    public List<Route> getByStartAndEndPoints(Long startPointId, Long endPointId) {
        List<Route> routes = routeDAO.getByStartAndEndPoints(startPointId, endPointId);
        if(routes.isEmpty()){
            logger.error("No routes found for start point id {} and end point id {}", startPointId, endPointId);
        }
       return routes;
    }

    @Override
    public Route getByStartEndAndVehicle(Long startPointId, Long endPointId, Long vehicleId) {
        return routeDAO.getByStartEndAndVehicle(startPointId, endPointId, vehicleId).get();
    }

    @Override
    public List<Route> getAll() {
        List<Route> routes = routeDAO.getAll();
        if(routes.isEmpty()){
            logger.error("No routes found");
        }
        return routes;
    }

    @Override
    public Integer countTotal() {
        return routeDAO.countTotal().get();
    }

    @Override
    protected boolean isValidData(Route entity) {
        return entity != null &&
                entity.getStartPointId() != null && entity.getStartPointId() > 0 &&
                entity.getEndPointId() != null && entity.getEndPointId() > 0 &&
                entity.getVehicleId() != null && entity.getVehicleId() > 0 &&
                entity.getDistance() != null && entity.getDistance() > 0;
    }
}
