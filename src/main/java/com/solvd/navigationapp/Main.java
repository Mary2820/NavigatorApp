package com.solvd.navigationapp;
import com.solvd.navigationapp.models.Client;
import com.solvd.navigationapp.models.User;
import com.solvd.navigationapp.utils.parsers.DataParser;
import com.solvd.navigationapp.utils.parsers.JAXBParser;
import com.solvd.navigationapp.utils.parsers.JacksonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";

    public static void main(String[] args) {
        Client client = new Client(1L, "Jan", "Mazowiecki", "jan.maz@example.com", "password123");

        DataParser<User> jacksonparser = new JacksonParser();
        DataParser<User> jaxbparser = new JAXBParser();

        jacksonparser.writeToFile(JSON_FILE_PATH, client);
        jaxbparser.writeToFile(XML_FILE_PATH, client);

        User parsedjacksonuser = jacksonparser.readFromFile(JSON_FILE_PATH, User.class);

        User parsedjaxbuser = jaxbparser.readFromFile(XML_FILE_PATH, User.class);

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