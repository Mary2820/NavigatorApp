package com.solvd.navigationapp.models;

import java.time.LocalDate;

public class License {
    private Long id;
    private String number;
    private LocalDate expirationDate;

    public License(Long id, String number, LocalDate expirationDate) {
        this.id = id;
        this.number = number;
        this.expirationDate = expirationDate;
    }

    public License() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
