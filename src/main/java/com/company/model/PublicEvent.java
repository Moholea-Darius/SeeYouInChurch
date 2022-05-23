package com.company.model;

import java.time.LocalDateTime;

public class PublicEvent extends Event {

    private int roomId;

    public PublicEvent(int id, String name, LocalDateTime startDate, LocalDateTime endDate, int roomId) {
        super(id, name, startDate, endDate);
        this.roomId = roomId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "PublicEvent{" +
                "roomId=" + roomId +
                super.toString() +
                '}';
    }

}
