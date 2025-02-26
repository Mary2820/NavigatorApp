package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Location;

import java.util.List;

public interface ILocationDAO extends IGenericDAO<Location, Long> {
    List<Location> getByCityId(Long cityId);

    List<Location> getByName(String name);

    List<Location> getByAddress(String address);

    List<Location> getByType(LocationType type);

    List<Location> getByCityIdAndType(Long cityId, LocationType type);

    List<Location> getAllLocationsInCity(Long cityId);

    int countLocationsByType(LocationType type);

    int countLocationsInCity(Long cityId);
}
