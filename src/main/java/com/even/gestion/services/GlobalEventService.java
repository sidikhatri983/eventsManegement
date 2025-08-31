package com.even.gestion.services;

import com.even.gestion.models.GlobalEventDTO;
import com.even.gestion.models.GlobalEventResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GlobalEventService {
    private static final Logger logger = LoggerFactory.getLogger(GlobalEventService.class);
    private final RestTemplate restTemplate;
    private static final String OPEN_DATA_API_URL = "https://opendata.paris.fr/api/records/1.0/search/";

    public GlobalEventService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GlobalEventDTO> getEventsByCity(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(OPEN_DATA_API_URL)
                .queryParam("dataset", "que-faire-a-paris-")  // Dataset for events in Paris
                .queryParam("q", city)
                .queryParam("rows", 10)  // You can adjust the number of rows
                .toUriString();

        try {
            // Fetching the data from the API
            GlobalEventResponse response = restTemplate.getForObject(url, GlobalEventResponse.class);

            if (response == null || response.getRecords().isEmpty()) {
                logger.info("No global events found for city: {}", city);
                return List.of();  // Return an empty list if no events are found
            }

            // Convert response to list of GlobalEventDTOs
            return response.toEventDTOs("Global (Open Data)");

        } catch (Exception e) {
            logger.error("Error fetching global events for city: {}", city, e);
            return List.of();  // Return an empty list in case of an error
        }
    }
}
