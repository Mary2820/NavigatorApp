package com.solvd.navigationapp.utils.algorithms;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {
    private static final Logger logger = LogManager.getLogger(PathFinder.class.getName());

    private PathFinder() {}

    public static List<Location> getShortPath(Graph graph, Location start, Location end) {
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }

        Map<Location, Integer> distances = new HashMap<>();
        Map<Location, Location> predecessors = new HashMap<>();
        PriorityQueue<Map.Entry<Location, Integer>> queue = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));

        graph.getLocations().forEach(location -> distances.put(location, Integer.MAX_VALUE));
        distances.put(start, 0);
        queue.add(new SimpleEntry<>(start, 0));

        while (!queue.isEmpty()) {
            Location current = queue.poll().getKey();
            if (current.equals(end)) break;

            for (Route route : graph.getRoutesFromLocation(current)) {
                Location neighbor = getNeighborLocation(graph, route, current);
                if (neighbor == null) continue;

                int newDist = distances.get(current) + route.getDistance();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    queue.add(new SimpleEntry<>(neighbor, newDist));
                }
            }
        }
        return reconstructPath(start, end, predecessors);
    }

    private static Location getNeighborLocation(Graph graph, Route route, Location current) {
        if (route.getStartPointId().equals(current.getId())) {
            return graph.getLocationById(route.getEndPointId());
        } else if (route.getEndPointId().equals(current.getId()) && route.isBidirectional()) {
            return graph.getLocationById(route.getStartPointId());
        }
        return null;
    }

    private static List<Location> reconstructPath(Location start, Location end, Map<Location, Location> previous) {
        if (!previous.containsKey(end) && !start.equals(end)) {
            return new ArrayList<>();
        }

        List<Location> path = new ArrayList<>();
        for (Location currentLoc = end; currentLoc != null; currentLoc = previous.get(currentLoc)) {
            path.add(currentLoc);
        }
        Collections.reverse(path);

        logPath(path);
        return path;
    }

    private static void logPath(List<Location> path) {
        String pathString = path.stream()
                .map(Location::getName)
                .collect(Collectors.joining(" -> "));

        logger.info("Short path: {}", pathString);
    }
}
