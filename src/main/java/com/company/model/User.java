package com.company.model;

import com.company.model.constants.Department;
import com.company.model.constants.Sex;
import com.company.model.constants.UserType;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String surname;
    private Sex sex;
    private String phoneNumber;
    private String email;
    private String address;
    private UserType type;
    private List<Department> departments;
    private List<Notification> notifications;
    private List<Group> groups;

    public User(int id, String name, String surname, Sex sex, String phoneNumber, String email, String address, UserType type, List<Department> departments, List<Notification> notifications, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.type = type;
        this.departments = departments;
        this.notifications = notifications;
        this.groups = groups;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                ", departments=" + departments +
                ", notifications=" + notifications +
                ", groups=" + groups +
                '}';
    }
}
