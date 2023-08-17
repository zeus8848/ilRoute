package com.github.ilRoute.dto;

public class User {

    public User() {
    }

    public User(String name, int number) {
        this.name = name;
        this.number = number;
    }

    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
