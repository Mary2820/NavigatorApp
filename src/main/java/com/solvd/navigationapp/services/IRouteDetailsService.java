package com.solvd.navigationapp.services;

import com.solvd.navigationapp.models.Route;

import java.util.List;

public interface IRouteDetailsService {
    void saveRoutes(List<Route> routeList);
}
