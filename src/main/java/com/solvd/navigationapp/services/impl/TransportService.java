package com.solvd.navigationapp.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.constants.TransportSpeedConstants;
import com.solvd.navigationapp.enums.VehicleType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.models.Vehicle;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.services.dbservices.impl.RouteService;
import com.solvd.navigationapp.services.dbservices.impl.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TransportService implements ITransportService {
    private static final Logger logger = LogManager.getLogger(TransportService.class.getName());
    private final IRouteService routeService;
    private final IVehicleService vehicleService;

    public TransportService() {
        this.routeService = new RouteService();
        this.vehicleService = new VehicleService();
    }

    @Override
    public List<Route> getTransportPath(List<Location> path) {
        List<Route> transportPath = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            Location currentLocation = path.get(i);
            Location nextLocation = path.get(i + 1);
            Route bestRoute = findBestRouteForType(currentLocation.getId(), nextLocation.getId());

            if (bestRoute != null) {
                transportPath.add(bestRoute);

                String bestTransport = getTransportName(bestRoute);
                logger.info("The best transport between {} and {} is {}",
                        currentLocation.getName(),
                        nextLocation.getName(), 
                        bestTransport);
            } else {
                logger.warn("No route found between {} and {}",
                        currentLocation.getName(), 
                        nextLocation.getName());
            }
        }

        return transportPath;
    }

    private String getTransportName(Route route) {
        Long vehicleTypeId = getVehicleTypeId(route);
        return (vehicleTypeId == null) ? "WALK" : VehicleType.getById(vehicleTypeId).getName();
    }

    private Route findBestRouteForType(Long startPointId, Long endPointId) {
        return getRoutesBetweenLocations(startPointId, endPointId)
                .stream()
                .min(Comparator.comparingDouble(route -> route.getDistance() / getSpeedForRoute(route)))
                .orElse(null);
    }

    private List<Route> getRoutesBetweenLocations(Long startPointId, Long endPointId) {
        List<Route> routes = routeService.getByStartAndEndPoints(startPointId, endPointId);

        List<Route> reverseRoutes = routeService.getByStartAndEndPoints(endPointId, startPointId)
                .stream()
                .filter(Route::isBidirectional)
                .toList();

        List<Route> allRoutes = new ArrayList<>(routes);
        allRoutes.addAll(reverseRoutes);

        return allRoutes;
    }

    private Integer getSpeedForRoute(Route route) {
        Optional<Vehicle> vehicle = vehicleService.getById(route.getVehicleId());
        Long vehicleTypeId = vehicle.get().getVehicleTypeId();
        return (vehicleTypeId == null)
                ? TransportSpeedConstants.WALK_SPEED
                : VehicleType.getById(vehicleTypeId).getSpeed();
    }

    private Long getVehicleTypeId(Route route) {
        Optional<Vehicle> vehicleOpt = vehicleService.getById(route.getVehicleId());
        return vehicleOpt.map(Vehicle::getVehicleTypeId).orElse(null);
    }
}

