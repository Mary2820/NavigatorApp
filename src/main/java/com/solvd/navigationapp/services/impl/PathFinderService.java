package com.solvd.navigationapp.services.impl;

import java.util.List;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.utils.algorithms.IPathFinder;
import com.solvd.navigationapp.utils.algorithms.PathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinderService implements IPathFinderService {
    private static final Logger logger = LogManager.getLogger(PathFinderService.class);
    private final IPathFinder pathFinder;
    private final ITransportService transportService;

    public PathFinderService() {
        this.transportService = new TransportService();

        Graph graph = new GraphService().getGraph();
        this.pathFinder = new PathFinder(graph);
    }

    public List<Route> getBestPath(Location startLocation, Location endLocation) {
        List<Location> path = pathFinder.getShortPath(startLocation, endLocation);

        if (path.isEmpty()) {
            logger.warn("No path found between {} and {}", startLocation, endLocation);
            throw new IllegalStateException("No path found between the given locations.");
        }

        logger.info("Shortest path found with {} locations", path.size());

        return transportService.getTransportPath(path);
    }
}
