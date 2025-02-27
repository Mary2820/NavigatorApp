package com.solvd.navigationapp.DAO;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
    void insert(T entity);

    Optional<T> getById(Long id);

    void update(T entity);

    void delete(Long id);
}