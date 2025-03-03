package com.solvd.navigationapp.models;

import java.util.Objects;

public class Route {
    private static final int MASK = 1;
    private Long id;
    private Long startPointId;
    private Long endPointId;
    private Long vehicleId;
    private Integer distance;
    private boolean isBidirectional;

    public Route(Long id, Long startPointId, Long endPointId, Long vehicleId, Integer distance, boolean isBidirectional) {
        this.id = id;
        this.startPointId = startPointId;
        this.endPointId = endPointId;
        this.vehicleId = vehicleId;
        this.distance = distance;
        this.isBidirectional = true;
    }

    public Route() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartPointId() {
        return startPointId;
    }

    public void setStartPointId(Long startPointId) {
        this.startPointId = startPointId;
    }

    public Long getEndPointId() {
        return endPointId;
    }

    public void setEndPointId(Long endPointId) {
        this.endPointId = endPointId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public boolean isBidirectional() {
        return isBidirectional;
    }

    public void setBidirectional(boolean bidirectional) {
        isBidirectional = bidirectional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (hashCode() != o.hashCode()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(startPointId, route.startPointId) &&
                Objects.equals(endPointId, route.endPointId) && Objects.equals(vehicleId, route.vehicleId) &&
                Objects.equals(distance, route.distance) && isBidirectional == route.isBidirectional;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startPointId, endPointId, vehicleId, distance, isBidirectional);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", startId=" + startPointId +
                ", endId=" + endPointId +
                ", vehicleId=" + vehicleId +
                ", distance=" + distance +
                ", isBidirectional=" + isBidirectional +
                '}';
    }

}
