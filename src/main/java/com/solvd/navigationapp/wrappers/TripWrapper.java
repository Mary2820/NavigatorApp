package com.solvd.navigationapp.wrappers;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.navigationapp.models.RouteDetails;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@JsonRootName("trip")
@XmlRootElement(name = "trip")
@XmlAccessorType(XmlAccessType.FIELD)
public class TripWrapper {

    @XmlElementWrapper(name = "trip stages")
    @XmlElement(name = "trip stage")
    private List<RouteDetails> routeDetails;

    public List<RouteDetails> getTripStages() {
        return routeDetails;
    }

    public void setTripStages(List<RouteDetails> routeDetails) {
        this.routeDetails = routeDetails;
    }
}
