package com.solvd.navigationapp.utils.algorithms;

import java.util.List;

import com.solvd.navigationapp.models.Location;

public interface IPathFinder {
    Integer getShortestPathDistance(Location start, Location end);

    List<Location> findShortestPath(Location start, Location end);

    List<Location> getShortPath();
}
