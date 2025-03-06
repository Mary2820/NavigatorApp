package com.solvd.navigationapp;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.dbservices.impl.LocationService;
import com.solvd.navigationapp.services.impl.PathFinderService;
import com.solvd.navigationapp.services.impl.RouteDetailsService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        findBestPath("Central Library", "West Side Tram Stop");
    }

    public static void findBestPath(String startPoint, String endPoint){
        LocationService locationService = new LocationService();
        Location start = locationService.getByName(startPoint).get(0);
        Location end = locationService.getByName(endPoint).get(0);

        IPathFinderService pathFinderService = new PathFinderService();
        List<Route> routeList = pathFinderService.getBestPath(start, end);
        RouteDetailsService routeDetailsService = new RouteDetailsService();
        routeDetailsService.saveRoutes(routeList);
    }
}