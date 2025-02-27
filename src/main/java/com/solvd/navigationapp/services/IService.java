package com.solvd.navigationapp.services;

import java.util.Optional;

public interface IService<T> {
    void insert(T entity);

    Optional<T> getById(Long id);

    void update(T entity);

    void delete(Long id);
}
