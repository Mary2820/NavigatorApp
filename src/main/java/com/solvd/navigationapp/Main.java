package com.solvd.navigationapp;
import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.services.dbservices.impl.LocationService;
import com.solvd.navigationapp.services.dbservices.impl.RouteService;
import com.solvd.navigationapp.services.dbservices.impl.VehicleService;
import com.solvd.navigationapp.services.impl.GraphService;
import com.solvd.navigationapp.services.impl.PathFinderService;
import com.solvd.navigationapp.services.impl.RouteDetailsService;
import com.solvd.navigationapp.services.impl.TransportService;
import com.solvd.navigationapp.utils.algorithms.PathFinder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        findBestPath("Central Library", "West Side Tram Stop");
    }

    public static void findBestPath(String startPoint, String endPoint){
        try{
            ILocationService locationService = new LocationService();

            Location start = locationService.getByName(startPoint).get(0);
            Location end = locationService.getByName(endPoint).get(0);

            IRouteService routeService = new RouteService();
            IGraphService graphService = new GraphService(locationService, routeService);
            Graph graph = graphService.getGraphFromDatabase();

            IVehicleService vehicleService = new VehicleService();
            ITransportService transportService = new TransportService(routeService,vehicleService);
            IPathFinderService pathFinderService = new PathFinderService(transportService);

            List<Route> routeList = pathFinderService.getBestPath(graph, start, end);

            RouteDetailsService routeDetailsService = new RouteDetailsService(locationService, vehicleService);
            routeDetailsService.saveRoutes(routeList);
        } catch (Exception e) {
            logger.error("Catched error:", e);
        }
    }
}