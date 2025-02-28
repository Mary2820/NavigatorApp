package com.solvd.navigationapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Location, List<Route>> locationRoutes = new HashMap<>();

    public void addLocation(Location location) {
        if (!locationRoutes.containsKey(location)) {
            locationRoutes.put(location, new ArrayList<>());
        }
    }

    public void addRoute(Route route) {
        Location startLocation = getLocationById(route.getStartPointId());
        Location endLocation = getLocationById(route.getEndPointId());
        locationRoutes.get(startLocation).add(route);

        if (route.isBidirectional()) {
            locationRoutes.get(endLocation).add(route);
        }
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

    public Location getLocationById(Long id) {
        return locationRoutes.keySet().stream()
                .filter(location -> location.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }

    public Set<Location> getLocations() {
        return locationRoutes.keySet();
    }

    public List<Route> getRoutesFromLocation(Location location) {
        return locationRoutes.getOrDefault(location, new ArrayList<>());
    }
}
