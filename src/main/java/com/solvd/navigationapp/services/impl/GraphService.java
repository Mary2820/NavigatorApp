package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GraphService implements IGraphService {
    private static final Logger logger = LogManager.getLogger(GraphService.class);
    private Graph graph;
    private final ILocationService locationService;
    private final IRouteService routeService;
    private boolean isGraphLoaded = false;

    public GraphService(ILocationService locationService, IRouteService routeService) {
        this.graph = new Graph();
        this.locationService = locationService;
        this.routeService = routeService;
    }

    @Override
    public Graph getGraph() {
        if (!isGraphLoaded) {
            try {
                loadGraph();
                isGraphLoaded = true;
            } catch (Exception e) {
                logger.error("Failed to load graph: {}", e.getMessage(), e);
            }
        }
        return graph;
    }

    private void loadGraph() {
        List<Location> locations = locationService.getAll();
        List<Route> routes = routeService.getAll();

        if (locations == null || locations.isEmpty()) {
            logger.warn("No locations found. Graph may be incomplete.");
        } else {
            locations.forEach(graph::addLocation);
        }

        if (routes == null || routes.isEmpty()) {
            logger.warn("No routes found. Graph may be incomplete.");
        } else {
            routes.forEach(graph::addRoute);
        }

        logger.info("Graph loaded with {} locations and {} routes",
                graph.getLocations().size(), routes.size());
    }
}
