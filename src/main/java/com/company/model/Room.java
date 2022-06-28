package com.company.model;

import com.company.model.constants.Status;

import java.util.Objects;

public class Room {

    private int id;
    private int number;
    private int maxCapacity ;
    private Status status;

    public Room() {}

    public Room(int id, int number, int maxCapacity, Status status) {
        this.id = id;
        this.number = number;
        this.maxCapacity = maxCapacity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && number == room.number && maxCapacity == room.maxCapacity && status == room.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, maxCapacity, status);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", maxCapacity=" + maxCapacity +
                ", status=" + status +
                '}';
    }

}
