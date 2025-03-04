package com.solvd.navigationapp.services.examples;

import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.impl.PathFinderService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PathFinderExample {
    private static final Logger logger = LogManager.getLogger(PathFinderExample.class.getName());

    public static void main(String[] args) {
       /*  Graph graph = new Graph();

        Location locationA = new Location(1L, "Location A", 1L, "Address A", LocationType.BUILDING);
        Location locationB = new Location(2L, "Location B", 1L, "Address B", LocationType.BUS_STOP);
        Location locationC = new Location(3L, "Location C", 1L, "Address C", LocationType.TRAM_STOP);
        Location locationD = new Location(4L, "Location D", 1L, "Address D", LocationType.BUILDING);
        Location locationE = new Location(5L, "Location E", 1L, "Address E", LocationType.BUS_STOP);

        graph.addLocation(locationA);
        graph.addLocation(locationB);
        graph.addLocation(locationC);
        graph.addLocation(locationD);
        graph.addLocation(locationE);

        Route routeAB = new Route(1L, 1L, 2L, 1L, 10);
        Route routeAC = new Route(2L, 1L, 3L, 1L, 15);
        Route routeBC = new Route(3L, 2L, 3L, 1L, 5);
        Route routeBD = new Route(4L, 2L, 4L, 1L, 12);
        Route routeCD = new Route(5L, 3L, 4L, 1L, 8);
        Route routeDE = new Route(6L, 4L, 5L, 1L, 7);
        Route routeCE = new Route(7L, 3L, 5L, 1L, 20);

        graph.addRoute(routeAB);
        graph.addRoute(routeAC);
        graph.addRoute(routeBC);
        graph.addRoute(routeBD);
        graph.addRoute(routeCD);
        graph.addRoute(routeDE);
        graph.addRoute(routeCE);

        PathFinderService pathFinder = new PathFinderService(graph);

        List<Location> shortestPath = pathFinder.findShortestPath(locationA, locationE);

        logger.info("Shortest path from {} to {}:", locationA.getName(), locationE.getName());
        for (int i = 0; i < shortestPath.size(); i++) {
            logger.info(shortestPath.get(i).getName());
            if (i < shortestPath.size() - 1) {
                logger.info(" -> ");
            }
        }

        int totalDistance = pathFinder.getShortestPathDistance(locationA, locationE);
        logger.info("Total distance: {}", totalDistance);

        Location locationF = new Location(6L, "Location F", 1L, "Address F", LocationType.BUILDING);
        graph.addLocation(locationF);
        
        List<Location> nonExistentPath = pathFinder.findShortestPath(locationA, locationF);
        if (nonExistentPath.isEmpty()) {
            logger.info("No path exists from {} to {}", locationA.getName(), locationF.getName());
        }*/
    }
} 