package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Location;

import java.util.List;

public interface ILocationDAO extends IDAO<Location> {
    List<Location> getByCityId(Long cityId);

    List<Location> getByName(String name);

    List<Location> getByAddress(String address);

    List<Location> getByType(LocationType type);

    List<Location> getAll();
}