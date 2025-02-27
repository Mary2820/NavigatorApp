package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Route;

public interface IRouteService extends IService<Route> {
    List<Route> getByStartPointId(Long startPointId);

    List<Route> getByEndPointId(Long endPointId);

    List<Route> getByVehicleId(Long vehicleId);

    List<Route> getByStartAndEndPoints(Long startPointId, Long endPointId);

    Route getByStartEndAndVehicle(Long startPointId, Long endPointId, Long vehicleId);

    int countTotal();
}
