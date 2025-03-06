package com.solvd.navigationapp.enums;

import java.util.Objects;

public enum VehicleType {
    BUS(1L, "BUS", 60),
    TRAIN(2L, "TRAIN", 120),
    TRAM(3L, "TRAM", 40),
    TAXI(4L, "TAXI", 90);

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

    public static String getById(Long id) {
        for (VehicleType vehicleType : values()) {
            if (Objects.equals(vehicleType.getId(), id)) {
                return vehicleType.name;
            }
        }
        throw new IllegalArgumentException("No vehicle with ID: " + id);
    }
}
