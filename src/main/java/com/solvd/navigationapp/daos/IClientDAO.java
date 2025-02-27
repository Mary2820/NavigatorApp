package com.solvd.navigationapp.daos;

import com.solvd.navigationapp.models.Client;

import java.util.List;

public interface IClientDAO extends IDAO<Client> {
    Client getByEmail(String email);

    List<Client> getByFirstName(String firstName);

    List<Client> getByLastName(String lastName);

    List<Client> getByNamePart(String namePart);

    List<Client> getAllUsers();

    boolean isEmailTaken(String email);

    void deleteByEmail(String email);
   
}