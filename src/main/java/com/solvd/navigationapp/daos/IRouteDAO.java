package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface IRouteDAO extends IDAO<Route> {
    List<Route> getByStartPointId(Long startPointId);

    List<Route> getByEndPointId(Long endPointId);

    List<Route> getByVehicleId(Long vehicleId);

    Optional<Route> getByStartAndEndPoints(@Param("startPointId") Long startPointId, @Param("endPointId") Long endPointId);

    Optional<Route> getByStartEndAndVehicle(@Param("startPointId") Long startPointId, @Param("endPointId") Long endPointId, @Param("vehicleId") Long vehicleId);

    List<Route> getAll();

    Optional<Integer> countTotal();
}