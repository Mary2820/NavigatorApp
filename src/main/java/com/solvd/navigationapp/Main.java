package com.solvd.navigationapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.navigationapp.models.User;
import com.solvd.navigationapp.models.utils.DataParser;
import com.solvd.navigationapp.models.utils.JAXBParser;
import com.solvd.navigationapp.models.utils.JacksonParser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        User user = new User(1L, 1L, "Jan", "Mazowiecki", "jan.maz@example.com", "password123");

        DataParser<User> jacksonparser = new JacksonParser();
        DataParser<User> jaxbparser = new JAXBParser();

        jacksonparser.writeToFile("src/main/resources/data.json",user);
        jaxbparser.writeToFile("src/main/resources/data.xml",user);

        User parsedjacksonuser = jacksonparser.readFromFile("src/main/resources/data.json", User.class);

        User parsedjaxbuser = jaxbparser.readFromFile("src/main/resources/data.xml", User.class);

        if (parsedjacksonuser != null && parsedjaxbuser != null) {
            logger.info("User read from JSON: " + parsedjacksonuser.getFirstName() + " " + parsedjacksonuser.getLastName());
            logger.info("User read from XML: " + parsedjaxbuser.getFirstName() + " " + parsedjaxbuser.getLastName());
        } else {
            if (parsedjacksonuser == null) {
                logger.error("Failed to read user from JSON.");
            }
            if (parsedjaxbuser == null) {
                logger.error("Failed to read user from XML.");
            }
        }
    }
}

