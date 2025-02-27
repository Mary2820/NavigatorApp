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
    public static void main(String[] args) {
        Client client = new Client(1L, "Jan", "Mazowiecki", "jan.maz@example.com", "password123");

        DataParser<User> jacksonparser = new JacksonParser();
        DataParser<User> jaxbparser = new JAXBParser();

        jacksonparser.writeToFile("src/main/resources/data.json",client);
        jaxbparser.writeToFile("src/main/resources/data.xml", client);

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