package com.solvd.navigationapp.utils.parsers;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Optional;


public class JAXBParser<T> implements IDataParser<T> {
    private static final Logger logger = LogManager.getLogger(JAXBParser.class.getName());

    @Override
    public void writeToFile(String filePath, T data) {
        try {
            JAXBContext context = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(filePath));
            logger.info("XML saved to {}", filePath);
        } catch (JAXBException e) {
            logger.error(e);
        }
    }

    @Override
    public Optional<T> readFromFile(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return Optional.of(clazz.cast(unmarshaller.unmarshal(new File(filePath))));
        } catch (JAXBException e) {
            logger.error("Error during reading XML file: ", e);
        }
        return Optional.empty();
    }
}