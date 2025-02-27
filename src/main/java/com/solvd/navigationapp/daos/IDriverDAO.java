package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Driver;

import java.util.List;

public interface IDriverDAO extends IDAO<Driver> {
    Driver getByLicenseId(Long licenseId);

    List<Driver> getByFullName(String firstName, String lastName);

}
