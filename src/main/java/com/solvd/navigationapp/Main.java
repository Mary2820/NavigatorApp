package com.solvd.navigationapp;
import com.solvd.navigationapp.daos.mybatisimpl.LocationDAO;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.services.INavigationService;
import com.solvd.navigationapp.services.IPathFinderService;
import com.solvd.navigationapp.services.impl.GraphService;
import com.solvd.navigationapp.services.impl.NavigationService;
import com.solvd.navigationapp.services.impl.PathFinderService;
import com.solvd.navigationapp.utils.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";

    public static void main(String[] args) {
//        Client client = new Client(1L, "Jan", "Mazowiecki", "jan.maz@example.com",
//                "password123");
//
//        IDataParser<User> jacksonparser = new JacksonParser<User>();
//        IDataParser<User> jaxbparser = new JAXBParser<User>();
//
//        jacksonparser.writeToFile(JSON_FILE_PATH, client);
//        jaxbparser.writeToFile(XML_FILE_PATH, client);
//
//        Optional<User> parsedjacksonuser = jacksonparser.readFromFile(JSON_FILE_PATH, User.class);
//
//        Optional<User> parsedjaxbuser = jaxbparser.readFromFile(XML_FILE_PATH, User.class);
//
//        parsedjacksonuser.ifPresentOrElse(user -> logger.info("User read from JSON: " + user.getFirstName() + " " +
//                user.getLastName()),() -> logger.error("Failed to read user from JSON.") );
//        parsedjaxbuser.ifPresentOrElse(user -> logger.info("User read from XML: " + user.getFirstName() + " " +
//                user.getLastName()), () -> logger.error("Failed to read user from XML.") );

            Location start = DAOFactory.getInstance().getLocationDAO().getById(83L).orElseThrow();
            Location end = DAOFactory.getInstance().getLocationDAO().getById(99L).orElseThrow();

            //GraphService graphService = new GraphService();
            //graphService.loadGraph();

            IPathFinderService pathFinderService = new PathFinderService();
            pathFinderService.addStartLocation(start);
            pathFinderService.addEndLocation(end);
            INavigationService navigationService = new NavigationService(pathFinderService);
            navigationService.showPath();

            /*logger.info("Searching path from {} to {}", start.getName(), end.getName());
            List<Location> path = navigationService.showPath();

            if (!path.isEmpty()) {
                logger.info("Found path with {} stops:", path.size() - 1);
                for (int i = 0; i < path.size(); i++) {
                    logger.info("{}. {}", i + 1, path.get(i).getName());
                }
                logger.info("Total distance: {}", pathFinderService.getShortestPathDistance());
            } else {
                logger.info("No path found between locations");
            }
        } catch (Exception e) {
            logger.error("Error during navigation: {}", e.getMessage());
        }*/
    } 
}