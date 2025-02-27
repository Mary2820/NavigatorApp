package com.solvd.navigationapp.utils;
import com.solvd.navigationapp.models.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class JAXBParser <T> implements DataParser<T> {
    private static final Logger logger = LogManager.getLogger(JAXBParser.class.getName());

    @Override
    public void writeToFile(String filePath, T data) {
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
    public T readFromFile(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            logger.error(e);
        }
        return null;
    }
}