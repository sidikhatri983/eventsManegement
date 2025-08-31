package com.even.gestion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlobalEventRecord {
    @JsonProperty("fields")
    private GlobalEventFields fields;

    public GlobalEventDTO toGlobalEventDTO(String source) {
        return new GlobalEventDTO(fields.title, fields.location, fields.description, fields.date, source);
    }
}

