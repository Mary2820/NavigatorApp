package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

public interface ITransportService {
    List<Route> getTransportPath(List<Location> path);
}