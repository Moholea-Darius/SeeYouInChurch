package com.company.model;

import com.company.model.constants.AnnouncementType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Announcement {

    private int id;
    private String sender;
    private String message;
    private AnnouncementType type;
    private LocalDateTime sentDate;

    public Announcement(int id, String sender, String message, AnnouncementType type, LocalDateTime sentDate) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.type = type;
        this.sentDate = sentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AnnouncementType getType() {
        return type;
    }

    public void setType(AnnouncementType type) {
        this.type = type;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Announcement that = (Announcement) o;
        return id == that.id && Objects.equals(sender, that.sender) && Objects.equals(message, that.message) && type == that.type && Objects.equals(sentDate, that.sentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, message, type, sentDate);
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", sentDate=" + sentDate +
                '}';
    }

}
