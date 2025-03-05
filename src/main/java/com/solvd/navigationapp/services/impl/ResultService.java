package com.solvd.navigationapp.services.impl;

import com.solvd.navigationapp.enums.VehicleType;
import com.solvd.navigationapp.models.Location;
import com.solvd.navigationapp.models.Result;
import com.solvd.navigationapp.models.Route;
import com.solvd.navigationapp.models.Vehicle;
import com.solvd.navigationapp.services.dbservices.ILocationService;
import com.solvd.navigationapp.services.dbservices.IVehicleService;
import com.solvd.navigationapp.services.dbservices.impl.LocationService;
import com.solvd.navigationapp.services.dbservices.impl.VehicleService;
import com.solvd.navigationapp.utils.parsers.IDataParser;
import com.solvd.navigationapp.utils.parsers.JAXBParser;
import com.solvd.navigationapp.utils.parsers.JacksonParser;
import com.solvd.navigationapp.wrappers.ResultWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultService {
    private static final Logger logger = LogManager.getLogger(ResultService.class);
    private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private static final String XML_FILE_PATH = "src/main/resources/data/data.xml";
    private final ILocationService locationService;
    private final IVehicleService vehicleService;

    public ResultService() {
        this.locationService = new LocationService();
        this.vehicleService = new VehicleService();
    }

    public List<Result> convertRoutesToResults(List<Route> routeList) {
        List<Result> results = new ArrayList<>();
        
        for (Route route : routeList) {
            try {
                Optional<Location> startPoint = locationService.getById(route.getStartPointId());
                Optional<Location> endPoint = locationService.getById(route.getEndPointId());
                
                if (startPoint.isEmpty() || endPoint.isEmpty()) {
                    logger.warn("Could not find location for route {}", route.getId());
                    continue;
                }

                String vehicleType = "WALK";
                String vehicleNumber = "";
                
                if (route.getVehicleId() != null) {
                    Optional<Vehicle> vehicle = vehicleService.getById(route.getVehicleId());
                    if (vehicle.isPresent()) {
                        vehicleType = getVehicleTypeName(vehicle.get().getVehicleTypeId());
                        vehicleNumber = vehicle.get().getRegistrationNumber();
                    }
                }

                // Calculate time in minutes based on distance (assuming average speed of 5 km/h for walking)
                Integer timeInMinutes = (int) (route.getDistance() / 83.33); // Convert meters to minutes at 5 km/h

                Result result = new Result(
                    startPoint.get(),
                    endPoint.get(),
                    route.getDistance(),
                    timeInMinutes,
                    vehicleType,
                    vehicleNumber
                );
                
                results.add(result);
            } catch (Exception e) {
                logger.error("Error processing route {}: {}", route.getId(), e.getMessage());
            }
        }
        return results;
    }

    private String getVehicleTypeName(Long vehicleTypeId) {
        return VehicleType.getById(vehicleTypeId);
    }

    public void saveResult(List<Route> routeList) {
        try {
            List<Result> results = convertRoutesToResults(routeList);
            
            ResultWrapper resultWrapper = new ResultWrapper();
            resultWrapper.setResults(results);
            
            IDataParser<ResultWrapper> jacksonParser = new JacksonParser<>();
            IDataParser<ResultWrapper> jaxbParser = new JAXBParser<>();

            jacksonParser.writeToFile(JSON_FILE_PATH, resultWrapper);
            jaxbParser.writeToFile(XML_FILE_PATH, resultWrapper);
            
            logger.info("Results successfully saved to JSON and XML files");
        } catch (Exception e) {
            logger.error("Error saving results: {}", e.getMessage(), e);
        }
    }
}
