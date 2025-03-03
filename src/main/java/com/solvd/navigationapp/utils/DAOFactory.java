package com.solvd.navigationapp.utils;

import com.solvd.navigationapp.daos.mybatisimpl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private CityDAO cityDAO;
    private ClientDAO clientDAO;
    private DriverDAO driverDAO;
    private LicenseDAO licenseDAO;
    private LocationDAO locationDAO;
    private RouteDAO routeDAO;
    private VehicleDAO vehicleDAO;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public CityDAO getCityDAO() {
        if (cityDAO == null) {
            cityDAO = new CityDAO();
        }
        return cityDAO;
    }

    public ClientDAO getClientDAO() {
        if (clientDAO == null) {
            clientDAO = new ClientDAO();
        }
        return clientDAO;
    }

    public DriverDAO getDriverDAO() {
        if (driverDAO == null) {
            driverDAO = new DriverDAO();
        }
        return driverDAO;
    }

    public LicenseDAO getLicenseDAO() {
        if (licenseDAO == null) {
            licenseDAO = new LicenseDAO();
        }
        return licenseDAO;
    }

    public LocationDAO getLocationDAO() {
        if (locationDAO == null) {
            locationDAO = new LocationDAO();
        }
        return locationDAO;
    }

    public RouteDAO getRouteDAO() {
        if (routeDAO == null) {
            routeDAO = new RouteDAO();
        }
        return routeDAO;
    }

    public VehicleDAO getVehicleDAO() {
        if (vehicleDAO == null) {
            vehicleDAO = new VehicleDAO();
        }
        return vehicleDAO;
    }
}
