package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.License;
import java.time.LocalDate;
import java.util.List;

public interface ILicenceDAO extends IGenericDAO<License, Long> {

    License getByNumber(String number);

    List<License> getExpiredLicenses(LocalDate currentDate);

    List<License> getValidLicenses(LocalDate currentDate);

    List<License> getLicensesByExpirationRange(LocalDate startDate, LocalDate endDate);

    int countExpiredLicenses(LocalDate currentDate);

    int countValidLicenses(LocalDate currentDate);
}
