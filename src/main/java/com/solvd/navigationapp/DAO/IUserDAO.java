package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.User;

import java.util.List;

public interface IUserDAO extends IDAO<User> {
    User getByEmail(String email);

    List<User> getByFirstName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByNamePart(String namePart);

    boolean isEmailTaken(String email);

    void updatePassword(Long id, String newPassword);

    void deleteByEmail(String email);

    List<User> getAllUsers();
}