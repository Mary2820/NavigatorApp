package com.solvd.navigationapp.services;
import com.solvd.navigationapp.models.Location;

import java.util.List;

public class TransportService {

    private final NavigationService navigationService;

    public TransportService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    public String determineBestTransport(Location start, Location end) {

        List<Location> path = navigationService.findPath(start, end);
        int numberOfStops = path.size() - 1;
        String transportMode = getTransportTypeForRoute(numberOfStops);

        return String.format("Best transport is: %s, with %d stops", transportMode, numberOfStops);
    }

    private String getTransportTypeForRoute(int numberOfStops) {
        if (numberOfStops < 1) {
            return "By foot";
        } else if (numberOfStops < 10) {
            return "Bus";
        } else {
            return "Taxi";
        }
    }
}