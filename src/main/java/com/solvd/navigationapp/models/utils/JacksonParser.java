package com.solvd.navigationapp.models.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.navigationapp.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser implements DataParser<User> {
    private static final Logger logger = LogManager.getLogger(JacksonParser.class.getName());
    @Override
    public void writeToFile(String filePath, User data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
            logger.info("JSON saved to " + filePath);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    public User readFromFile(String filePath, Class<User> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), clazz);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
}