package com.solvd.navigationapp.services;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

public interface IPathFinderService {
    List<Route> getBestPath(Location startLocation, Location endLocation);
}
