package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Route;

import java.util.List;

public interface IRouteDAO extends IDAO<Route> {
    List<Route> getByStartPointId(Long startPointId);

    List<Route> getByEndPointId(Long endPointId);

    List<Route> getByVehicleId(Long vehicleId);

    List<Route> getByStartAndEndPoints(Long startPointId, Long endPointId);

    Route getByStartEndAndVehicle(Long startPointId, Long endPointId, Long vehicleId);

    List<Route> getAll();

    int countTotal();
}