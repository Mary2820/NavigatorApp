package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.daos.ILocationDAO;
import com.solvd.navigationapp.daos.IRouteDAO;
import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GraphService implements IGraphService {
    private static final Logger logger = LogManager.getLogger(GraphService.class);
    private final Graph graph;
    private final ILocationDAO locationDAO;
    private final IRouteDAO routeDAO;

    public GraphService() {
        this.graph = new Graph();
        this.locationDAO = DAOFactory.getInstance().getLocationDAO();
        this.routeDAO = DAOFactory.getInstance().getRouteDAO();
    }

    @Override
    public void loadGraph() {
        List<Location> locations = locationDAO.getAll();
        List<Route> routes = routeDAO.getAll();

        locations.forEach(graph::addLocation);
        routes.forEach(graph::addRoute);
        
        logger.info("Graph loaded with {} locations and {} routes", 
                   graph.getLocations().size(), routes.size());
    }

    @Override
    public Graph getGraph() {
        return graph;
    }
}
