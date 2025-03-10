package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

public interface IPathFinderService {
    List<Route> getBestPath(Graph graph, Location start, Location end);
}
