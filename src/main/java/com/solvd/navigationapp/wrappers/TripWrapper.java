package com.solvd.navigationapp.wrappers;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.navigationapp.models.RouteDetails;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@JsonRootName("trip")
@XmlRootElement(name = "trip")
@XmlAccessorType(XmlAccessType.FIELD)
public class TripWrapper {

    @XmlElementWrapper(name = "trip_stages")
    @XmlElement(name = "trip_stage")
    private List<RouteDetails> routeDetails;

    public List<RouteDetails> getRouteDetails() {
        return routeDetails;
    }

    public void setRouteDetails(List<RouteDetails> routeDetails) {
        this.routeDetails = routeDetails;
    }
}
