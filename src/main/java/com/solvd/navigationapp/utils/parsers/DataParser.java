package com.solvd.navigationapp.utils.parsers;

public interface DataParser<T> {
    void writeToFile(String filePath, T data);
    T readFromFile(String filePath, Class<T> clazz);
}
