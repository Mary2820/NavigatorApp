package com.solvd.navigationapp.services.impl;

import java.util.List;

import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.ITransportService;
import com.solvd.navigationapp.services.dbservices.IRouteService;
import com.solvd.navigationapp.services.dbservices.impl.RouteService;

public class TransportService implements ITransportService{
    private final IRouteService routeService;

    public TransportService() {
        this.routeService = new RouteService();
    }

    @Override
    public List<Route> getTransportPath(List<Location> path) {
        return routeService.getByEndPointId(99L);
    }
}
