package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.User;

import java.util.List;

public interface IPersonDAO extends IDAO<User> {

    List<User> getByFirstName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByFullName(String firstName, String lastName);

    int countTotal();
}