package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.Person;

import java.util.List;

public interface IPersonDAO extends IGenericDAO<Person, Long> {

    List<Person> getByFirstName(String firstName);

    List<Person> getByLastName(String lastName);

    List<Person> getByFullName(String firstName, String lastName);

    List<Person> getAllByFirstName(String firstName);

    List<Person> getAllByLastName(String lastName);

    int countTotal();
}
