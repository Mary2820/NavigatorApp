package com.solvd.navigationapp.models;

import java.time.LocalDate;

public class License {
    private Long id;
    private Long userId;
    private String number;
    private LocalDate expirationDate;

    public License(Long id, Long userId, String number, LocalDate expirationDate) {
        this.id = id;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
