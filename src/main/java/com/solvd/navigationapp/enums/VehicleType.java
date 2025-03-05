package com.solvd.navigationapp.enums;

import java.util.Objects;

public enum VehicleType {
    BUS(1L, "BUS"),
    TRAIN(2L, "TRAIN"),
    TRAM(3L, "TRAM"),
    TAXI(4L, "TAXI");

    private final Long id;
    private final String name;

    VehicleType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
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
