package com.solvd.navigationapp.enums;

import com.solvd.navigationapp.utils.constants.TransportSpeedConstants;

import java.util.Arrays;
import java.util.Objects;


public enum VehicleType {
    BUS(1L, "BUS", TransportSpeedConstants.BUS_SPEED),
    TRAIN(2L, "TRAIN", TransportSpeedConstants.TRAIN_SPEED),
    TRAM(3L, "TRAM", TransportSpeedConstants.TRAM_SPEED),
    TAXI(4L, "TAXI", TransportSpeedConstants.TAXI_SPEED);

    private final Long id;
    private final String name;
    private final Integer speed;

    VehicleType(Long id, String name, Integer speed) {
        this.id = id;
        this.name = name;
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public static VehicleType getById(Long id) {
        return Arrays.stream(values())
                .filter(vehicleType -> Objects.equals(vehicleType.getId(), id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No vehicle with ID: " + id));
    }
}
