package com.solvd.navigationapp.utils.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser <T> implements DataParser<T> {
    private static final Logger logger = LogManager.getLogger(JacksonParser.class.getName());
    @Override
    public void writeToFile(String filePath, T data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
            logger.info("JSON saved to " + filePath);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public T readFromFile(String filePath, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}