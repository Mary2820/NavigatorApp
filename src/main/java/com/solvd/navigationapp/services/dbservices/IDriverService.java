package com.solvd.navigationapp.services.dbservices;
import com.solvd.navigationapp.models.Driver;

import java.util.List;

public interface IDriverService extends IService<Driver> {
    List<Driver> getByFullName(String firstName, String lastName);
}
