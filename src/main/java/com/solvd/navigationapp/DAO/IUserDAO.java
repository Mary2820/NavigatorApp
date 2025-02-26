package com.solvd.navigationapp.DAO;

import com.solvd.navigationapp.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO extends IGenericDAO<User, Long> {
    User getByEmail(String email);

    List<User> getByFirstName(String firstName);

    List<User> getByLastName(String lastName);

    List<User> getByNamePart(String namePart); //Retrieves a list of users whose first name or last name partially matches the provided string - useful for search

    boolean isEmailTaken(String email);

    void updatePassword(Long id, String newPassword);

    void deleteByEmail(String email);

    List<User> getAllUsers();
}
