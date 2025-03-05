package com.solvd.navigationapp.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.navigationapp.enums.LocationType;
import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@JsonRootName(value = "location")
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.NONE)
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

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "cityId")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "type")
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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cityId=" + cityId +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
