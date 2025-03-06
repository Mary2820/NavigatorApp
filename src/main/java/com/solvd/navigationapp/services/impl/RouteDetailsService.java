package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.enums.VehicleType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.RouteDetails;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.models.Vehicle;
import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.utils.parsers.IDataParser;
import com.solvd.navigationapp.utils.parsers.JAXBParser;
import com.solvd.navigationapp.utils.parsers.JacksonParser;
import com.solvd.navigationapp.wrappers.TripWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RouteDetailsService {
    private static final Logger logger = LogManager.getLogger(RouteDetailsService.class);
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";
    private final ILocationService locationService;
    private final IVehicleService vehicleService;

    public RouteDetailsService(ILocationService locationService, IVehicleService vehicleService) {
        this.locationService = locationService;
        this.vehicleService = vehicleService;
    }

    public List<RouteDetails> convertRoutesToResults(List<Route> routeList) {
        List<RouteDetails> routeDetailsList = new ArrayList<>();
        
        for (Route route : routeList) {
            try {
                Location startPoint = locationService.getById(route.getStartPointId());
                Location endPoint = locationService.getById(route.getEndPointId());
                
                String vehicleType = "WALK";
                String vehicleNumber = "";
                
                if (route.getVehicleId() != null) {
                    Vehicle vehicle = vehicleService.getById(route.getVehicleId());
                    vehicleType = getVehicleTypeName(vehicle.getVehicleTypeId());
                    vehicleNumber = vehicle.getRegistrationNumber();
                    
                }

                // Calculate time in minutes based on distance (assuming average speed of 5 km/h for walking)
                Integer timeInMinutes = (int) (route.getDistance() / 83.33); // Convert meters to minutes at 5 km/h

                RouteDetails routeDetails = new RouteDetails(
                    startPoint,
                    endPoint,
                    route.getDistance(),
                    timeInMinutes,
                    vehicleType,
                    vehicleNumber
                );
                
                routeDetailsList.add(routeDetails);
            } catch (Exception e) {
                logger.error("Error processing route {}: {}", route.getId(), e.getMessage());
            }
        }
        return routeDetailsList;
    }

    private String getVehicleTypeName(Long vehicleTypeId) {
        return VehicleType.getById(vehicleTypeId);
    }

    public void saveResult(List<Route> routeList) {
        try {
            List<RouteDetails> routeDetailsList = convertRoutesToResults(routeList);
            
            TripWrapper tripWrapper = new TripWrapper();
            tripWrapper.setTripStages(routeDetailsList);
            
            IDataParser<TripWrapper> jacksonParser = new JacksonParser<>();
            IDataParser<TripWrapper> jaxbParser = new JAXBParser<>();

            jacksonParser.writeToFile(JSON_FILE_PATH, tripWrapper);
            jaxbParser.writeToFile(XML_FILE_PATH, tripWrapper);
            
            logger.info("Results successfully saved to JSON and XML files");
        } catch (Exception e) {
            logger.error("Error saving results: {}", e.getMessage(), e);
        }
    }
}
