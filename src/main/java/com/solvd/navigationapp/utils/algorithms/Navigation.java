package com.solvd.navigationapp.utils.algorithms;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.navigationapp.models.Location;

public class Navigation {
 private static final Logger logger = LogManager.getLogger(Navigation.class);
    public Navigation() {
    }

    public void showPath(List<Location> path, Location start, Location end, Integer shortestPathDistance) {
        //List<Location> path = pathFinder.findShortestPath(start, end);
        try {
            logger.info("Searching path from {} to {}", start.getName(), end.getName());

            logger.info("Route planned from {} to {}, {} stops", 
                       start.getName(), end.getName(), path.size() - 1 );

            if (!path.isEmpty()) {
                logger.info("Found path with {} stops:", path.size() - 1);
                for (int i = 0; i < path.size(); i++) {
                    logger.info("{}. {}", i + 1, path.get(i).getName());
                }
                logger.info("Total distance: {}", shortestPathDistance);
            } else {
                logger.info("No path found between locations");
            }
        } catch (Exception e) {
            logger.error("Error during navigation: {}", e.getMessage());
        //return path;
    }

    }
    
}
