package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.License;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILicenseDAO extends IDAO<License> {

    Optional<License> getByNumber(String number);
    List<License> getExpiredLicenses(LocalDate currentDate);
    List<License> getValidLicenses(LocalDate currentDate);
    List<License> getAll();
}