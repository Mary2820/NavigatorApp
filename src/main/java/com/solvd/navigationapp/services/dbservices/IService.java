package com.solvd.navigationapp.services.dbservices;


public interface IService<T> {
    T getById(Long id);
    boolean save(T entity);
    boolean update(T entity);
    boolean deleteById(Long id);
}
