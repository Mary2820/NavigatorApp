package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Driver;

public interface IDriverSerivce extends IService<Driver>{
    Driver getByLicenseId(Long licenseId);

    List<Driver> getByFullName(String firstName, String lastName);
}
