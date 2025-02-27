package com.solvd.navigationapp.models.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.navigationapp.Main;
import com.solvd.navigationapp.models.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;


public class JAXBParser implements DataParser<User> {
    private static final Logger logger = LogManager.getLogger(JAXBParser.class.getName());

    @Override
    public void writeToFile(String filePath, User data) {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(filePath));
            logger.info("XML saved to " + filePath);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

    @Override
    public User readFromFile(String filePath, Class<User> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (User) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            logger.error(e);
        }
        return null;
    }
}