package com.solvd.navigationapp.models;

public class Route {
    private Long id;
    private Long startPointId;
    private Long endPointId;
    private Long vehicleId;

    public Route(Long id, Long startPointId, Long endPointId, Long vehicleId) {
        this.id = id;
        this.startPointId = startPointId;
        this.endPointId = endPointId;
        this.vehicleId = vehicleId;
    }

    public Route() {
    }

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
}
