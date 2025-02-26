package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.City;

import java.util.List;

public interface ICityDAO extends IGenericDAO<City, Long> {
    List<City> getByName(String name);

    List<City> getByRegion(String region);

    List<City> getByCountry(String country);

    List<City> getByCountryAndRegion(String country, String region);

    int countCitiesInRegion(String region);

    int countCitiesInCountry(String country);
}
