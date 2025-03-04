package com.solvd.navigationapp.services.impl;

import java.util.List;

import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.services.INavigationService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.utils.algorithms.Navigation;

public class NavigationService implements INavigationService {
    private Navigation navigation;
    private IPathFinderService pathFinderService;
    private List<Location> path;
    

    public NavigationService(IPathFinderService pathFinderService) {
        this.navigation = new Navigation();
        this.pathFinderService = pathFinderService;
       
    }
    public void showPath() {
        Location start = pathFinderService.getStartPoint();
        Location end = pathFinderService.getEndPoint();
        Integer shortestPathDistance = pathFinderService.getShortestPathDistance();
        path = pathFinderService.getShortPath();
        navigation.showPath(path,start, end, shortestPathDistance);
    }
}
