package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Location;

public interface IPathFinderService {
    Integer getShortestPathDistance();
    Location getStartPoint();
    Location getEndPoint();
    List<Location> getShortPath();
    void addEndLocation(Location starLocation);
    void addStartLocation(Location endLocation);
}
