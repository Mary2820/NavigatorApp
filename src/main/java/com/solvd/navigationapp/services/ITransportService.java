package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Location;

public interface ITransportService {

    List<Location> getTransportPath(List<Location> path);

}
