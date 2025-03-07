package com.solvd.navigationapp.services.dbservices;

import com.solvd.navigationapp.models.License;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ILicenseService extends IService<License> {
    License getByNumber(String number);
    List<License> getExpiredLicenses(LocalDate currentDate);
    List<License> getValidLicenses(LocalDate currentDate);
    List<License> getAll();
}
