package com.solvd.navigationapp;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.impl.PathFinderService;
import com.solvd.navigationapp.services.impl.RouteDetailsService;
import com.solvd.navigationapp.utils.DAOFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Location start = DAOFactory.getInstance().getLocationDAO().getById(83L);
        Location end = DAOFactory.getInstance().getLocationDAO().getById(99L);

        IPathFinderService pathFinderService = new PathFinderService();
        List<Route> routeList = pathFinderService.getBestPath(start, end);
        RouteDetailsService routeDetailsService = new RouteDetailsService();
        routeDetailsService.saveResult(routeList);
    } 
}