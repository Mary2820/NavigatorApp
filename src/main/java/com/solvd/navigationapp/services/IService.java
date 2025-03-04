package com.solvd.navigationapp.services;

import java.util.Optional;

public interface IService<T> {
    Optional<T> getById(Long id);
    boolean save(T entity);
    boolean update(T entity);
    boolean deleteById(Long id);
}
