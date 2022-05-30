package com.company.model;

import java.time.LocalDateTime;

public class PublicEvent extends Event {

    private Room roomId;

    public PublicEvent(int id, String name, LocalDateTime startDate, LocalDateTime endDate, Room roomId) {
        super(id, name, startDate, endDate);
        this.roomId = roomId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "PublicEvent{" +
                "roomId=" + roomId +
                '}';
    }
}
