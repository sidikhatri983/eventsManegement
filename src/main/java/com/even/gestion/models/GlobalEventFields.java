package com.even.gestion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalEventFields {
    @JsonProperty("title")
    public String title;

    @JsonProperty("address")
    public String location;

    @JsonProperty("description")
    public String description;

    @JsonProperty("date_start")
    public String date;
}

