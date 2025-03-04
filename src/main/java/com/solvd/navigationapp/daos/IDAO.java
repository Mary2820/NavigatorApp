package com.solvd.navigationapp.daos;

public interface IDAO<T> {
    void insert(T entity);

    T getById(Long id);

    void update(T entity);

    void deleteById(Long id);
}