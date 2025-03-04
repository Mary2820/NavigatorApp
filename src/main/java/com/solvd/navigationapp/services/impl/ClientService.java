package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.daos.IClientDAO;
import com.solvd.navigationapp.models.Client;
import com.solvd.navigationapp.services.IClientService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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
        
        try {
            clientDAO.insert(client);
            logger.info("Client successfully registered: {}", client.getEmail());
            return true;
        } catch (Exception e) {
            logger.error("Error registering client: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Client> getById(Long id) {
        try {
            return Optional.ofNullable(clientDAO.getById(id));
        } catch (Exception e) {
            logger.error("Error finding client by ID: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> getByEmail(String email) {
        try {
            Client client = clientDAO.getByEmail(email);
            return Optional.ofNullable(client);
        } catch (Exception e) {
            logger.error("Error finding client by email: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Client> getByFirstName(String firstName) {
        try {
            return clientDAO.getByFirstName(firstName);
        } catch (Exception e) {
            logger.error("Error finding clients by first name: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Client> getByLastName(String lastName) {
        try {
            return clientDAO.getByLastName(lastName);
        } catch (Exception e) {
            logger.error("Error finding clients by last name: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Client> getByNamePart(String namePart) {
        try {
            return clientDAO.getByNamePart(namePart);
        } catch (Exception e) {
            logger.error("Error finding clients by name part: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(Client client) {
        Optional<Client> existingClient = getById(client.getId());
        if (existingClient.isEmpty()) {
            logger.warn("Client with ID {} not found", client.getId());
            return false;
        }

        if (!existingClient.get().getEmail().equals(client.getEmail()) && isEmailTaken(client.getEmail())) {
            logger.warn("Email already taken: {}", client.getEmail());
            return false;
        }
        
        try {
            clientDAO.update(client);
            logger.info("Client successfully updated: {}", client.getId());
            return true;
        } catch (Exception e) {
            logger.error("Error updating client: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(Long clientId) {
        if (getById(clientId).isEmpty()) {
            logger.warn("Attempt to delete non-existent client with ID: {}", clientId);
            return false;
        }
        
        try {
            clientDAO.deleteById(clientId);
            logger.info("Client successfully deleted: {}", clientId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting client: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAccountByEmail(String email) {
        if (getByEmail(email).isEmpty()) {
            logger.warn("Attempt to delete non-existent client with email: {}", email);
            return false;
        }
        
        try {
            clientDAO.deleteByEmail(email);
            logger.info("Client successfully deleted by email: {}", email);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting client by email: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<Client> getAllClients() {
        try {
            return clientDAO.getAllUsers();
        } catch (Exception e) {
            logger.error("Error getting all clients: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    private boolean isEmailTaken(String email) {
        try {
            return clientDAO.isEmailTaken(email);
        } catch (Exception e) {
            logger.error("Error checking if email is taken: {}", e.getMessage());
            return false;
        }
    }

    @Override
    protected boolean isValidData(Client client) {
        if (client.getEmail() == null || client.getEmail().trim().isEmpty() ||
            client.getFirstName() == null || client.getFirstName().trim().isEmpty() ||
            client.getLastName() == null || client.getLastName().trim().isEmpty()) {
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