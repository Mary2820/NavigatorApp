package com.solvd.navigationapp.services;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Location;

public interface IPathFinderService {
    List<Location> getBestPath(Location startLocation, Location endLocation);
    Optional<Location> getStartPoint();
    Optional<Location> getEndPoint();
 
}
