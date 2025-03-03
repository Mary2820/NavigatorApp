package com.solvd.navigationapp.services;

import com.solvd.navigationapp.daos.mybatisimpl.LocationDAO;
import com.solvd.navigationapp.daos.mybatisimpl.RouteDAO;
import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GraphService implements IGraphService {
    private static final Logger logger = LogManager.getLogger(GraphService.class);
    private final Graph graph;
    private final LocationDAO locationDAO;
    private final RouteDAO routeDAO;

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
