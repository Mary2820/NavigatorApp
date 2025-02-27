package com.solvd.navigationapp.models.utils;

public interface DataParser<T> {
    void writeToFile(String filePath, T data);
    T readFromFile(String filePath, Class<T> clazz);
}
