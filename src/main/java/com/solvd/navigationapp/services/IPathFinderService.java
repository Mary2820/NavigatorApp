package com.solvd.navigationapp.services;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Location;

public interface IPathFinderService {
    Optional<Integer> getShortestPathDistance();
    Optional<Location> getStartPoint();
    Optional<Location> getEndPoint();
    List<Location> getShortPath();
    void addEndLocation(Location starLocation);
    void addStartLocation(Location endLocation);
}
