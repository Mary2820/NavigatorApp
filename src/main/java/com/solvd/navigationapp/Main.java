package com.solvd.navigationapp;
import com.solvd.navigationapp.models.Client;
import com.solvd.navigationapp.models.User;
import com.solvd.navigationapp.utils.parsers.IDataParser;
import com.solvd.navigationapp.utils.parsers.JAXBParser;
import com.solvd.navigationapp.utils.parsers.JacksonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";

    public static void main(String[] args) {
        Client client = new Client(1L, "Jan", "Mazowiecki", "jan.maz@example.com",
                "password123");

        IDataParser<User> jacksonparser = new JacksonParser<User>();
        IDataParser<User> jaxbparser = new JAXBParser<User>();

        jacksonparser.writeToFile(JSON_FILE_PATH, client);
        jaxbparser.writeToFile(XML_FILE_PATH, client);

        Optional<User> parsedjacksonuser = jacksonparser.readFromFile(JSON_FILE_PATH, User.class);

        Optional<User> parsedjaxbuser = jaxbparser.readFromFile(XML_FILE_PATH, User.class);

        parsedjacksonuser.ifPresentOrElse(user -> logger.info("User read from JSON: " + user.getFirstName() + " " +
                user.getLastName()),() -> logger.error("Failed to read user from JSON.") );
        parsedjaxbuser.ifPresentOrElse(user -> logger.info("User read from XML: " + user.getFirstName() + " " +
                user.getLastName()), () -> logger.error("Failed to read user from XML.") );
    }
}