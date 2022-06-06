package com.company.model;

import com.company.model.constants.NotificationStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Notification {

    private int id;
    private String sender;
    private String title;
    private String message;
    private LocalDateTime sendDate;
    private LocalDateTime readDate;
    private NotificationStatus status;

    public Notification(int id, String sender, String title, String message, LocalDateTime sendDate, LocalDateTime readDate, NotificationStatus status) {
        this.id = id;
        this.sender = sender;
        this.title = title;
        this.message = message;
        this.sendDate = sendDate;
        this.readDate = readDate;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public LocalDateTime getReadDate() {
        return readDate;
    }

    public void setReadDate(LocalDateTime readDate) {
        this.readDate = readDate;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id && Objects.equals(sender, that.sender) && Objects.equals(title, that.title) && Objects.equals(message, that.message) && Objects.equals(sendDate, that.sendDate) && Objects.equals(readDate, that.readDate) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, title, message, sendDate, readDate, status);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", sendDate=" + sendDate +
                ", readDate=" + readDate +
                ", status=" + status +
                '}';
    }

}
