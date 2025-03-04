package com.solvd.navigationapp.utils.algorithms;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.solvd.navigationapp.models.Graph;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Route;

public class PathFinder implements IPathFinder {
private final Graph graph;
private List<Location> path;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Location> findShortestPath(Location start, Location end) {
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }

        Map<Location, Integer> distances = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();
        PriorityQueue<Map.Entry<Location, Integer>> queue = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));

        graph.getLocations().forEach(loc -> distances.put(loc, Integer.MAX_VALUE));
        distances.put(start, 0);
        queue.add(new SimpleEntry<>(start, 0));

        while (!queue.isEmpty()) {
            Location current = queue.poll().getKey();
            if (current.equals(end)) break;

            for (Route route : graph.getRoutesFromLocation(current)) {
                Location neighbor = getNeighborLocation(route, current);
                if (neighbor == null) continue;

                int newDist = distances.get(current) + route.getDistance();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(new SimpleEntry<>(neighbor, newDist));
                }
            }
        }

        return reconstructPath(start, end, previous);
    }
    
    @Override
    public Integer getShortestPathDistance(Location start, Location end) {
        this.path = findShortestPath(start, end);
        if (path.isEmpty()) return -1;

        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Location current = path.get(i);
            Location next = path.get(i + 1);

            for (Route route : graph.getRoutesFromLocation(current)) {
                if (isRouteConnecting(route, current, next)) {
                    distance += route.getDistance();
                    break;
                }
            }
        }
        return distance;
    }

    public List<Location> getShortPath(){
        return path;
    }

    private Location getNeighborLocation(Route route, Location current) {
        if (route.getStartPointId().equals(current.getId())) {
            return graph.getLocationById(route.getEndPointId());
        } else if (route.getEndPointId().equals(current.getId()) && route.isBidirectional()) {
            return graph.getLocationById(route.getStartPointId());
        }
        return null;
    }

    private List<Location> reconstructPath(Location start, Location end, Map<Location, Location> previous) {
        if (!previous.containsKey(end) && !start.equals(end)) {
            return new ArrayList<>();
        }

        List<Location> path = new ArrayList<>();
        for (Location currentLoc = end; currentLoc != null; currentLoc = previous.get(currentLoc)) {
            path.add(currentLoc);
        }
        Collections.reverse(path);
        return path;
    }
    
    private boolean isRouteConnecting(Route route, Location from, Location to) {
        return (route.getStartPointId().equals(from.getId()) && route.getEndPointId().equals(to.getId())) ||
               (route.getEndPointId().equals(from.getId()) && route.getStartPointId().equals(to.getId()) && route.isBidirectional());
    }
}
