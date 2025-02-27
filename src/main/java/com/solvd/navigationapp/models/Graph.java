package com.solvd.navigationapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<Location, List<Route>> locationRoutes = new HashMap<>();

    public void addLocation(Location location) {
        if (!locationRoutes.containsKey(location)) {
            locationRoutes.put(location, new ArrayList<>());
        }
    }

    public void addRoute(Route route) {
        Location startLocation = locationRoutes.keySet().stream()
                .filter(location -> location.getId().equals(route.getStartPointId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Start location not found in graph"));
        
        Location endLocation = locationRoutes.keySet().stream()
                .filter(location -> location.getId().equals(route.getEndPointId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("End location not found in graph"));

        locationRoutes.get(startLocation).add(route);
    }

    public void removeRoute(Route route) {
        for (List<Route> routes : locationRoutes.values()) {
            routes.removeIf(r -> r.getId().equals(route.getId()));
        }
    }

    public void removeLocation(Location location) {
        for (List<Route> routes : locationRoutes.values()) {
            routes.removeIf(route -> 
                route.getStartPointId().equals(location.getId()) || 
                route.getEndPointId().equals(location.getId())
            );
        }
        locationRoutes.remove(location);
    }
}
