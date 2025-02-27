package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.City;

import java.util.List;

public interface ICityDAO extends IDAO<City> {
    List<City> getByName(String name);

    List<City> getByRegion(String region);

    List<City> getByCountry(String country);
}
