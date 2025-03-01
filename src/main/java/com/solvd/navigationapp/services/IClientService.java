package com.solvd.navigationapp.services;

import java.util.List;
import java.util.Optional;

import com.solvd.navigationapp.models.Client;

public interface IClientService {

    boolean register(Client client);

    Optional<Client> findById(Long id);

    Optional<Client> findByEmail(String email);

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    List<Client> findByNamePart(String namePart);

    boolean updateProfile(Client client);

    boolean deleteAccountById(Long clientId);

    boolean deleteAccountByEmail(String email);

    List<Client> findAllClients();
}
