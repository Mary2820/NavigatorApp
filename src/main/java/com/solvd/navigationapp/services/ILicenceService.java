package com.solvd.navigationapp.services;

import java.time.LocalDate;
import java.util.List;

import com.solvd.navigationapp.models.License;

public interface ILicenceService extends IService<License>{
    License getByNumber(String number);

    List<License> getExpiredLicenses(LocalDate currentDate);

    List<License> getValidLicenses(LocalDate currentDate);
}
