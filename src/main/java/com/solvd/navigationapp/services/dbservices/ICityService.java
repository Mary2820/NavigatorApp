package com.solvd.navigationapp.services.dbservices;

import com.solvd.navigationapp.models.City;

import java.util.List;

public interface ICityService extends IService<City> {
    List<City> getByName(String name);

    List<City> getByRegion(String region);

    List<City> getByCountry(String country);

}
