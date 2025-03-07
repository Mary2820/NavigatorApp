package com.solvd.navigationapp.services.dbservices;

import java.util.List;

import com.solvd.navigationapp.models.City;

public interface ICityService extends IService<City> {
    List<City> getByName(String name);

    List<City> getByRegion(String region);

    List<City> getByCountry(String country);
}
