package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.Person;

import java.util.List;

public interface IPersonDAO extends IDAO<Person> {

    List<Person> getByFirstName(String firstName);

    List<Person> getByLastName(String lastName);

    List<Person> getByFullName(String firstName, String lastName);

    int countTotal();
}