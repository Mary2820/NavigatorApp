package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.License;

import java.time.LocalDate;
import java.util.List;

public interface ILicenceDAO extends IDAO<License> {

    License getByNumber(String number);

    List<License> getExpiredLicenses(LocalDate currentDate);

    List<License> getValidLicenses(LocalDate currentDate);
}