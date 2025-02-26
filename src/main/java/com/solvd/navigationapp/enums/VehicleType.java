package com.solvd.navigationapp.enums;

public enum VehicleType {
    BUS(1L, "BUS"),
    TRAIN(2L, "TRAIN"),
    TRAM(3L, "TRAM");

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
}
