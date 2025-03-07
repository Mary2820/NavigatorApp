package com.solvd.navigationapp.services.impl;

import java.util.List;


import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.utils.algorithms.IPathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinderService implements IPathFinderService {
    private static final Logger logger = LogManager.getLogger(PathFinderService.class.getName());
    private final IPathFinder pathFinder;
    private final ITransportService transportService;

    public PathFinderService(IPathFinder pathFinder, ITransportService transportService)  {
        this.transportService = transportService;
        this.pathFinder = pathFinder;
    }

    public List<Route> getBestPath(Location start, Location end) {
        List<Location> path = pathFinder.getShortPath(start, end);

        if (path.isEmpty()) {
            logger.warn("No path found between {} and {}", start, end);
            throw new IllegalStateException("No path found between the given locations.");
        }

        return transportService.getTransportPath(path);
    }
}
