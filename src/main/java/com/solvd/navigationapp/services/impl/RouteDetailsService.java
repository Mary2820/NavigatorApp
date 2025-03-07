package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.utils.constants.TransportSpeedConstants;
import com.solvd.navigationapp.enums.VehicleType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.RouteDetails;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.models.Vehicle;
import com.solvd.navigationapp.services.IRouteDetailsService;
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

public class RouteDetailsService implements IRouteDetailsService {
    private static final Logger logger = LogManager.getLogger(RouteDetailsService.class.getName());
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";
    private final ILocationService locationService;
    private final IVehicleService vehicleService;

    public RouteDetailsService(ILocationService locationService, IVehicleService vehicleService) {
        this.locationService = locationService;
        this.vehicleService = vehicleService;
    }

    @Override
    public void saveRoutes(List<Route> routes) {
        try {
            List<RouteDetails> routeDetailsList = getRouteDetails(routes);

            TripWrapper tripWrapper = new TripWrapper();
            tripWrapper.setRouteDetails(routeDetailsList);

            IDataParser<TripWrapper> jacksonParser = new JacksonParser<>();
            IDataParser<TripWrapper> jaxbParser = new JAXBParser<>();

            jacksonParser.writeToFile(JSON_FILE_PATH, tripWrapper);
            jaxbParser.writeToFile(XML_FILE_PATH, tripWrapper);

            logger.info("Results successfully saved to JSON and XML files");
        } catch (Exception e) {
            logger.error("Error saving results: {}", e.getMessage(), e);
        }
    }

    private List<RouteDetails> getRouteDetails(List<Route> routes) {
        List<RouteDetails> routeDetailsList = new ArrayList<>();
        
        for (Route route : routes) {
            try {
                Location startPoint = locationService.getById(route.getStartPointId());
                Location endPoint = locationService.getById(route.getEndPointId());

                String vehicleTypeName = "WALK";
                String vehicleNumber = "";
                Integer distance = route.getDistance();
                Integer timeInMinutes = distance / TransportSpeedConstants.WALK_SPEED;

                Long vehicleId = route.getVehicleId();
                if (vehicleId != null) {
                    Vehicle vehicle = vehicleService.getById(vehicleId);

                    Long vehicleTypeId = vehicle.getVehicleTypeId();
                    VehicleType vehicleType = VehicleType.getById(vehicleTypeId);

                    vehicleTypeName = vehicleType.getName();
                    vehicleNumber = vehicle.getRegistrationNumber();
                    timeInMinutes = distance / vehicleType.getSpeed();
                }

                RouteDetails routeDetails = new RouteDetails(
                    startPoint,
                    endPoint,
                    distance,
                    timeInMinutes,
                    vehicleTypeName,
                    vehicleNumber
                );
                
                routeDetailsList.add(routeDetails);
            } catch (Exception e) {
                logger.error("Error processing route {}: {}", route.getId(), e.getMessage());
            }
        }
        return routeDetailsList;
    }
}