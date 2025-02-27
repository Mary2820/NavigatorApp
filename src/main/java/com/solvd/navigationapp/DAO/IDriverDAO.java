package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.Driver;

import java.util.List;

public interface IDriverDAO extends IDAO<Driver> {
    Driver getByLicenseId(Long licenseId);

    List<Driver> getByFullName(String firstName, String lastName);

    Driver getByPersonId(Long personId);
}
