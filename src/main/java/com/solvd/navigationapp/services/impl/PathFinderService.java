package com.solvd.navigationapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.utils.algorithms.IPathFinder;
import com.solvd.navigationapp.utils.algorithms.PathFinder;

public class PathFinderService implements IPathFinderService {
    private final IPathFinder pathFinder;
    private final IGraphService graphService;
    private final Graph graph;
    private Location startLocation;
    private Location endLocation;
    private ITransportService transportService;
    private List<Location> finalPath;

    public PathFinderService() {
        this.graphService = new GraphService();
        loadGraph();
        this.graph = getGraph();
        this.pathFinder = new PathFinder(graph);
    }

    private List<Location> getShortPath(Location startLocation, Location endLocation) {
        return pathFinder.getShortPath(startLocation, endLocation);

    }

    public List<Location> getBestPath(Location startLocation, Location endLocation) {
        List<Location> path = getShortPath(startLocation, endLocation);
        return path;
        /*
         * transportService = new TransportService();
         * finalPath = transportService.getTransportPath(path);
         * return finalPath;
         */
    }

    @Override
    public Optional<Location> getStartPoint() {
        return Optional.ofNullable(startLocation);
    }

    @Override
    public Optional<Location> getEndPoint() {
        return Optional.ofNullable(endLocation);
    }

    private Graph getGraph() {
        return graphService.getGraph();
    }

    private void loadGraph() {
        graphService.loadGraph();
    }

}
