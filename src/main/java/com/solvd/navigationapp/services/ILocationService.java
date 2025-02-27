package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.enums.LocationType;
import com.solvd.navigationapp.models.Location;

public interface ILocationService extends  IService<Location>{
    List<Location> getByCityId(Long cityId);

    List<Location> getByName(String name);

    List<Location> getByAddress(String address);

    List<Location> getByType(LocationType type);
}
  

