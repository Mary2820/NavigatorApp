package com.solvd.navigationapp.services;

import com.solvd.navigationapp.models.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class NavigationService {
    private static final Logger logger = LogManager.getLogger(NavigationService.class);
    private final PathFinderService pathFinder;

    public NavigationService(PathFinderService pathFinder) {
        this.pathFinder = pathFinder;
    }

    public List<Location> findPath(Location start, Location end) {
        List<Location> path = pathFinder.findShortestPath(start, end);

        logger.info("Route planned from {} to {}, {} stops", 
                   start.getName(), end.getName(), path.size() - 1);
        return path;
    }
}