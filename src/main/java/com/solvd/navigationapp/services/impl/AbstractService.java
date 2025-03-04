package com.solvd.navigationapp.services.impl;

public abstract class AbstractService<T> {

    protected abstract  boolean isValidData(T entity);

}
