package com.solvd.navigationapp.daos.mybatisimpl;

import com.solvd.navigationapp.daos.IClientDAO;
import com.solvd.navigationapp.models.Client;

import java.util.List;
import java.util.Optional;

public class ClientDAO extends AbstractMyBatisDAO<IClientDAO> implements IClientDAO {
    @Override
    public Client getByEmail(String email) {
        return executeInSession(mapper -> mapper.getByEmail(email));
    }

    @Override
    public List<Client> getByFirstName(String firstName) {
        return executeInSession(mapper -> mapper.getByFirstName(firstName));
    }

    @Override
    public List<Client> getByLastName(String lastName) {
        return executeInSession(mapper -> mapper.getByLastName(lastName));
    }

    @Override
    public List<Client> getByNamePart(String namePart) {
        return executeInSession(mapper -> mapper.getByNamePart(namePart));
    }

    @Override
    public List<Client> getAllUsers() {
        return executeInSession(IClientDAO::getAllUsers);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return executeInSession(mapper -> mapper.isEmailTaken(email));
    }

    @Override
    public void deleteByEmail(String email) {
        executeInSessionVoid(mapper -> mapper.deleteByEmail(email));
    }

    @Override
    public void insert(Client entity) {
        executeInSessionVoid(mapper -> mapper.insert(entity));
    }

    @Override
    public Optional<Client> getById(Long id) {
        return executeInSession(mapper -> mapper.getById(id));
    }

    @Override
    public void update(Client entity) {
        executeInSessionVoid(mapper -> mapper.update(entity));
    }

    @Override
    public void delete(Long id) {
        executeInSessionVoid(mapper -> mapper.delete(id));
    }

    @Override
    protected Class<IClientDAO> getMapperClass() {
        return IClientDAO.class;
    }
} 