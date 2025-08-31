package com.even.gestion.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class GlobalEventResponse {

    @JsonProperty("records")
    private List<GlobalEventRecord> records;

    // Getter for records
    public List<GlobalEventRecord> getRecords() {
        return records;
    }

    // Setter for records
    public void setRecords(List<GlobalEventRecord> records) {
        this.records = records;
    }

    // Method to map records to DTOs
    public List<GlobalEventDTO> toEventDTOs(String source) {
        return records.stream()
                .map(record -> record.toGlobalEventDTO(source))
                .toList();
    }
}


