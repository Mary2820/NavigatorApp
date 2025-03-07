package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Driver;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IDriverDAO extends IDAO<Driver> {

    List<Driver> getByFullName( @Param("firstName") String firstName, @Param("lastName")String lastName);
}
