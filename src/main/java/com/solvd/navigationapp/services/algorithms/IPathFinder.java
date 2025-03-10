package com.solvd.navigationapp.services.algorithms;

import java.util.List;

import com.solvd.navigationapp.models.Location;

public interface IPathFinder {
    List<Location> getShortPath(Location start, Location end);
}
