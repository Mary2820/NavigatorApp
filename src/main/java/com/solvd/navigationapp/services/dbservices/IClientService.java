package com.solvd.navigationapp.services.dbservices;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Client;

public interface IClientService extends IService<Client> {

    Optional<Client> getByEmail(String email);

    List<Client> getByFirstName(String firstName);

    List<Client> getByLastName(String lastName);

    List<Client> getByNamePart(String namePart);

    boolean deleteAccountByEmail(String email);

    List<Client> getAllClients();
}
