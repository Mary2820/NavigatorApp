package com.solvd.navigationapp.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.solvd.navigationapp.enums.VehicleType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.services.dbservices.impl.RouteService;

public class TransportService implements ITransportService{
    private final IRouteService routeService;

    public TransportService() {
        this.routeService = new RouteService();
    }

    @Override
    public List<Route> getTransportPath(List<Location> path) {
        List<Route> transportPath = new ArrayList<>();
        for (int i = 0; i < path.size() - 1; i++) {
            Location currentLocation = path.get(i);
            Location nextLocation = path.get(i + 1);
            Route bestRoute = findBestRouteForType(currentLocation, nextLocation);

            if (bestRoute != null) {
                transportPath.add(bestRoute);
            }
        }

        return transportPath;
    }
    private Route findBestRouteForType(Location startLocation, Location endLocation) {
        return getRoutesBetweenLocations(startLocation.getId(), endLocation.getId())
                .stream()
                .min(Comparator.comparingDouble(route ->
                        route.getDistance() / VehicleType.values()[(int) (long) route.getVehicleId() - 1].getSpeed()
                ))
                .orElse(null);
    }
    private List<Route> getRoutesBetweenLocations(Long startPointId, Long endPointId) {
        return routeService.getByStartAndEndPoints(startPointId, endPointId);
    }
}

