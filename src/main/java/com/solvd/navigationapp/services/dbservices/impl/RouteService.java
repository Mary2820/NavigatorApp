package com.solvd.navigationapp.services.dbservices.impl;

import java.util.Collections;
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
    public Optional<Route> getById(Long id) {
        try {
            logger.info("Getting route by id: {}", id);
            return Optional.ofNullable(routeDAO.getById(id));
        } catch (Exception e) {
            logger.error("Error getting route by id {}: {}", id, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean save(Route entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid route data for saving");
            throw new IllegalArgumentException("Invalid route data");
        }
        try {
            logger.info("Saving route: {}", entity);
            routeDAO.insert(entity);
            return true;
        } catch (Exception e) {
            logger.error("Error saving route {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Route entity) {
        if (!isValidData(entity)) {
            logger.error("Invalid route data for updating");
            throw new IllegalArgumentException("Invalid route data");
        }
        try {
            Route existingRoute = routeDAO.getById(entity.getId());
            if (existingRoute == null) {
                logger.error("Route with id {} does not exist", entity.getId());
                throw new IllegalArgumentException("Route does not exist");
            }
            logger.info("Updating route: {}", entity);
            routeDAO.update(entity);
            return true;
        } catch (Exception e) {
            logger.error("Error updating route {}: {}", entity, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Route route = routeDAO.getById(id);
            if (route == null) {
                logger.error("Route with id {} does not exist", id);
                throw new IllegalArgumentException("Route does not exist");
            }
            logger.info("Deleting route: {}", route);
            routeDAO.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting route with id {}: {}", id, e.getMessage());
            return false;
        }
    }

    @Override
    public List<Route> getByStartPointId(Long startPointId) {
        try {
            logger.info("Getting routes by start point id: {}", startPointId);
            return routeDAO.getByStartPointId(startPointId);
        } catch (Exception e) {
            logger.error("Error getting routes by start point id {}: {}", startPointId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Route> getByEndPointId(Long endPointId) {
        try {
            logger.info("Getting routes by end point id: {}", endPointId);
            return routeDAO.getByEndPointId(endPointId);
        } catch (Exception e) {
            logger.error("Error getting routes by end point id {}: {}", endPointId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Route> getByVehicleId(Long vehicleId) {
        try {
            logger.info("Getting routes by vehicle id: {}", vehicleId);
            return routeDAO.getByVehicleId(vehicleId);
        } catch (Exception e) {
            logger.error("Error getting routes by vehicle id {}: {}", vehicleId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Route> getByStartAndEndPoints(Long startPointId, Long endPointId) {
        try {
            return routeDAO.getByStartAndEndPoints(startPointId, endPointId);
        } catch (Exception e) {
            logger.error("Error getting routes by start point id {} and end point id {}: {}", 
                startPointId, endPointId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Route> getByStartEndAndVehicle(Long startPointId, Long endPointId, Long vehicleId) {
        try {
            return Optional.ofNullable(routeDAO.getByStartEndAndVehicle(startPointId, endPointId, vehicleId));
        } catch (Exception e) {
            logger.error("Error getting route by start point id {}, end point id {} and vehicle id {}: {}", 
                startPointId, endPointId, vehicleId, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Route> getAll() {
        try {
            logger.info("Getting all routes");
            return routeDAO.getAll();
        } catch (Exception e) {
            logger.error("Error getting all routes: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Integer countTotal() {
        try {
            logger.info("Counting total routes");
            return routeDAO.countTotal();
        } catch (Exception e) {
            logger.error("Error counting total routes: {}", e.getMessage());
            return 0;
        }
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
