package com.solvd.navigationapp.services.impl;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.services.IGraphService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.utils.algorithms.IPathFinder;
import com.solvd.navigationapp.utils.algorithms.PathFinder;

public class PathFinderService implements IPathFinderService{
    private final IPathFinder pathFinder;
    private final IGraphService graphService;
    private final Graph graph;
    private Location startLocation;
    private Location endLocation;

    public PathFinderService() {
        this.graphService = new GraphService();
        loadGraph();
        this.graph = getGraph();
        this.pathFinder = new PathFinder(graph);
    }
    @Override
    public Optional<Integer> getShortestPathDistance(){
        return Optional.ofNullable(pathFinder.getShortestPathDistance(startLocation, endLocation));
    }
    @Override
    public void addStartLocation(Location starLocation) {
        this.startLocation = starLocation;
    }
    @Override
    public void addEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }
    @Override
    public List<Location> getShortPath(){
        return pathFinder.getShortPath();
        
    }
    @Override
    public Optional<Location> getStartPoint(){
        return Optional.ofNullable(startLocation);
    }
    
    @Override
    public Optional<Location> getEndPoint(){
        return Optional.ofNullable(endLocation);
    }
    
    private Graph getGraph() {
        return graphService.getGraph();
    }

    private void loadGraph() {
        graphService.loadGraph();
    }


}
