package com.company.model;

import java.time.LocalDateTime;

public class PrivateEvent extends Event{

    private String location;
    private String details;

    public PrivateEvent(int id, String name, LocalDateTime startDate, LocalDateTime endDate, String location, String details) {
        super(id, name, startDate, endDate);
        this.location = location;
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "PrivateEvent{" +
                "location='" + location + '\'' +
                ", details='" + details + '\'' +
                super.toString() +
                '}';
    }
}
