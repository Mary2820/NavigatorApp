package com.solvd.navigationapp.enums;

public enum UserType {
    CLIENT(1L, "CLIENT"),
    DRIVER(2L, "DRIVER");

    private final Long id;
    private final String name;

    UserType(Long id, String name) {
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
