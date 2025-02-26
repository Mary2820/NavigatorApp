package com.solvd.navigationapp.DAO;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T, K> {
    void insert(T entity);
    Optional<T> getById(K id);
    List<T> getAll();
    void update(T entity);
    void delete(K id);
}
