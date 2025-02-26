package com.solvd.navigationapp.enums;

public enum LocationType {
    BUILDING(1L, "BUILDING"),
    BUS_STOP(2L, "BUS_STOP"),
    TRAM_STOP(3L, "TRAM_STOP");

    private final Long id;
    private final String name;

    LocationType(Long id, String name) {
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
