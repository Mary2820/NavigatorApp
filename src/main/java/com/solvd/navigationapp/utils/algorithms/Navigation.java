package com.solvd.navigationapp.utils.algorithms;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.navigationapp.models.Location;

public class Navigation {
    private static final Logger logger = LogManager.getLogger(Navigation.class);

    public Navigation() {
    }

    public void showPath(List<Location> path, Optional<Location> start, Optional<Location> end,
            Optional<Integer> shortestPathDistance) {
        // List<Location> path = pathFinder.findShortestPath(start, end);
        try {
            if (start.isPresent() && end.isPresent()) {
                logger.info("Searching path from {} to {}", start.get().getName(), end.get().getName());

                logger.info("Route planned from {} to {}, {} stops",
                        start.get().getName(), end.get().getName(), path.size() - 1);

                if (!path.isEmpty()) {
                    logger.info("Found path with {} stops:", path.size() - 1);
                    for (int i = 0; i < path.size(); i++) {
                        logger.info("{}. {}", i + 1, path.get(i).getName());
                    }
                    logger.info("Total distance: {}", shortestPathDistance.orElse(-1));
                } else {
                    logger.info("No path found between locations");
                }
            } else {
                logger.warn("Start or end location is missing");
            }
        } catch (Exception e) {
            logger.error("Error during navigation: {}", e.getMessage(), e);
        }

    }

}
