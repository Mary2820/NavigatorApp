package com.solvd.navigationapp.services.dbservices.impl;

public abstract class AbstractService<T> {
    protected abstract boolean isValidData(T entity);
}
