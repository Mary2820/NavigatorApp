package com.solvd.navigationapp.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.xml.bind.annotation.*;

@JsonRootName(value = "trip stage")
@XmlRootElement(name = "trip stage")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "startPoint", "endPoint", "distance", "timeInMinutes", "vehicleType", "vehicleNumber"})
public class RouteDetails {
    private Location startPoint;
    private Location endPoint;
    private Integer distance;
    private Integer timeInMinutes;
    private String vehicleType;
    private String vehicleNumber;

    public RouteDetails(Location startPoint, Location endPoint, Integer distance, Integer timeInMinutes, String vehicleType, String vehicleNumber) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.timeInMinutes = timeInMinutes;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public RouteDetails() {
    }

    @XmlElement(name = "startPoint")
    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    @XmlElement(name = "endPoint")
    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }

    @XmlElement(name = "distance")
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @XmlElement(name = "timeInMinutes")
    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    @XmlElement(name = "vehicleType")
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @XmlElement(name = "vehicleNumber")
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
