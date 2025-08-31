package com.even.gestion.models;

public class GlobalEventDTO {
    private String title;
    private String location;
    private String description;
    private String date;
    private String source;

    // Constructor, Getters, and Setters

    public GlobalEventDTO() {
    }

    public GlobalEventDTO(String title, String location, String description, String date, String source) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.date = date;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
