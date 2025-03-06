package com.solvd.navigationapp.services.dbservices.impl;

import com.solvd.navigationapp.daos.IClientDAO;
import com.solvd.navigationapp.models.Client;
import com.solvd.navigationapp.services.dbservices.IClientService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ClientService extends AbstractService<Client> implements IClientService {
    private static final Logger logger = LogManager.getLogger(ClientService.class.getName());
    private final IClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = DAOFactory.getInstance().getClientDAO();
    }

    @Override
    public boolean save(Client client) {
        if (!isValidData(client)) {
            return false;
        }

        if (isEmailTaken(client.getEmail())) {
            logger.warn("Email already taken: {}", client.getEmail());
            return false;
        }
        clientDAO.insert(client);
        logger.info("Client successfully registered: {}", client.getEmail());
        return true;
    }

    @Override
    public Client getById(Long id) {
        return clientDAO.getById(id).get();
    }

    @Override
    public Client getByEmail(String email) {
        return clientDAO.getByEmail(email).get();
    }

    @Override
    public List<Client> getByFirstName(String firstName) {
        List<Client> clients = clientDAO.getByFirstName(firstName);
        if (clients.isEmpty()) {
            logger.error("Client not found by first name: {}", firstName);
            return Collections.emptyList();
        } else {
            logger.info("Client found by first name: {}", firstName);
            return clients;
        }

    }

    @Override
    public List<Client> getByLastName(String lastName) {
        List<Client> clients = clientDAO.getByLastName(lastName);
        if (clients.isEmpty()) {
            logger.error("Client not found by first name: {}", lastName);
            return Collections.emptyList();
        } else {
            logger.info("Client found by first name: {}", lastName);
            return clients;
        }
    }

    @Override
    public List<Client> getByNamePart(String namePart) {
        List<Client> clients = clientDAO.getByNamePart(namePart);
        if (clients.isEmpty()) {
            logger.error("Client not found by name part: {}", namePart);
        } else {
            logger.info("Client found by name part: {}", namePart);
        }
        return clients;
    }

    @Override
    public boolean update(Client client) {
        Optional<Client> existingClient = clientDAO.getById(client.getId());
        if (isValidData(client) && existingClient != null) {
            clientDAO.update(client);
            return true;
        }
        logger.error("Attempt to update non-existent client with ID: {}", client.getId());
        return false;
    }

    @Override
    public boolean deleteById(Long clientId) {
        if (clientDAO.getById(clientId) != null) {
            clientDAO.deleteById(clientId);
            return true;
        }
        logger.error("Error deleting client: {}", clientId);
        return false;
    }

    @Override
    public boolean deleteAccountByEmail(String email) {
        if (clientDAO.getByEmail(email) == null) {
            logger.error("Attempt to delete non-existent client with email: {}", email);
            return false;
        }
        clientDAO.deleteByEmail(email);
        logger.info("Client successfully deleted by email: {}", email);
        return true;
    }

    @Override
    public List<Client> getAllClients() {
        if (clientDAO.getAllUsers().isEmpty()) {
            logger.error("Error getting all clients");
            return Collections.emptyList();
        }
        return clientDAO.getAllUsers();
    }

    private boolean isEmailTaken(String email) {
        return clientDAO.isEmailTaken(email);
    }

    @Override
    protected boolean isValidData(Client client) {
        if (client.getEmail() == null || client.getEmail().trim().isEmpty() ||
                client.getFirstName() == null || client.getFirstName().trim().isEmpty() ||
                client.getLastName() == null || client.getLastName().trim().isEmpty() ||
                client.getPhoneNumber() == null || client.getPhoneNumber().trim().isEmpty()) {
            logger.warn("Missing required client fields");
            return false;
        }

        if (!isValidEmail(client.getEmail())) {
            logger.warn("Invalid email format: {}", client.getEmail());
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

}