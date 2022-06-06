package com.company.model;

import java.util.List;

public class Group {

    private int id;
    private String name;
    private String description;
    private List<PrivateEvent> privateEvents;
    private List<PublicEvent> publicEvents;

    public Group(int id, String name, String description, List<PrivateEvent> privateEvents, List<PublicEvent> publicEvents) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privateEvents = privateEvents;
        this.publicEvents = publicEvents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PrivateEvent> getPrivateEvents() {
        return privateEvents;
    }

    public void setPrivateEvents(List<PrivateEvent> privateEvents) {
        this.privateEvents = privateEvents;
    }

    public List<PublicEvent> getPublicEvents() {
        return publicEvents;
    }

    public void setPublicEvents(List<PublicEvent> publicEvents) {
        this.publicEvents = publicEvents;
    }
}
