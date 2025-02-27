package com.solvd.navigationapp.models;

import com.solvd.navigationapp.enums.LocationType;

import java.util.Objects;

public class Location {
    private static final int MASK = 1;
    private Long id;
    private String name;
    private Long cityId;
    private String address;
    private LocationType type;

    public Location(Long id, String name, Long cityId, String address, LocationType type) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.address = address;
        this.type = type;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (hashCode() != o.hashCode()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(name, location.name) &&
                Objects.equals(cityId, location.cityId) && Objects.equals(address, location.address) &&
                type == location.type;
    }

    @Override
    public int hashCode() {
        return MASK + Objects.hash(id, name, cityId, address, type);
    }

}
