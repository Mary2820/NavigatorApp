package com.solvd.navigationapp.models;

public class City {
    private Long id;
    private String name;
    private String region;
    private String country;

    public City(Long id, String name, String region, String country) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.country = country;
    }

    public City() {}

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
