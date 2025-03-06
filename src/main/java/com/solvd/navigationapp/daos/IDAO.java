package com.solvd.navigationapp.daos;

import java.util.Optional;

public interface IDAO<T> {
    void insert(T entity);

    Optional<T> getById(Long id);

    void update(T entity);

    void deleteById(Long id);
}