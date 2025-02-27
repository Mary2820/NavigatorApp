package com.solvd.navigationapp.services;

import java.util.List;

import com.solvd.navigationapp.models.Client;

public interface IClientService extends IService<Client> {
    Client getByEmail(String email);

    List<Client> getByFirstName(String firstName);

    List<Client> getByLastName(String lastName);

    List<Client> getByNamePart(String namePart);

    List<Client> getAllUsers();

    boolean isEmailTaken(String email);

    void deleteByEmail(String email);
}
