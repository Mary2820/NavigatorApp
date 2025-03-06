package com.solvd.navigationapp;

import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.services.dbservices.impl.LocationService;
import com.solvd.navigationapp.services.dbservices.impl.RouteService;
import com.solvd.navigationapp.services.dbservices.impl.VehicleService;
import com.solvd.navigationapp.services.impl.GraphService;
import com.solvd.navigationapp.services.impl.PathFinderService;
import com.solvd.navigationapp.services.impl.RouteDetailsService;
import com.solvd.navigationapp.utils.algorithms.PathFinder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.services.impl.TransportService;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try{
            ILocationService locationService = new LocationService();
            IRouteService routeService = new RouteService();
            IGraphService graphService = new GraphService(locationService, routeService);
            Graph graph = graphService.getGraph();
            PathFinder pathFinder = new PathFinder(graph);
            ITransportService transportService = new TransportService();
            IPathFinderService pathFinderService = new PathFinderService(pathFinder, transportService);
            Location start = locationService.getById(83L);
            Location end = locationService.getById(99L);
            List<Route> routeList = pathFinderService.getBestPath(start, end);
            IVehicleService vehicleService = new VehicleService();
            RouteDetailsService routeDetailsService = new RouteDetailsService(locationService, vehicleService);
            routeDetailsService.saveResult(routeList);
        } catch (Exception e) {
           logger.error("Catched error:", e);
        }
        /*
         * IClientService clientService = new ClientService();
         * clientService.getByLastName("Adammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
         * IDriverService driverService = new DriverService();
         * driverService.getByFullName("Adam", "Adam");
         */

        /*
         * Location start = DAOFactory.getInstance().getLocationDAO().getById(83L);
         * Location end = DAOFactory.getInstance().getLocationDAO().getById(99L);
         * 
         * 
         * List<Route> routeList = pathFinderService.getBestPath(start, end);
         * RouteDetailsService routeDetailsService = new RouteDetailsService();
         * routeDetailsService.saveResult(routeList);
         */

    }
}