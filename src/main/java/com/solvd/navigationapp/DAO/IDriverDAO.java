package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.Driver;

import java.util.List;

public interface IDriverDAO extends IGenericDAO<Driver, Long> {
    Driver getByLicenseId(Long licenseId);

    List<Driver> getByFullName(String firstName, String lastName);

    List<Driver> getByLicenseType(Long licenseId);

    List<Driver> getByVehicleId(Long vehicleId);

    Driver getByPersonId(Long personId);

    int countDriversWithLicense(Long licenseId);
}
