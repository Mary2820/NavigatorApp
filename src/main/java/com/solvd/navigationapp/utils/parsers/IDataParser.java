package com.solvd.navigationapp.utils.parsers;

import java.util.Optional;

public interface IDataParser<T> {
    void writeToFile(String filePath, T data);
    Optional<T> readFromFile(String filePath, Class<T> clazz);
}
