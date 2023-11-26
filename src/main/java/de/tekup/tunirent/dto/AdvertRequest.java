package de.tekup.tunirent.dto;

import de.tekup.tunirent.enums.LodgingType;
import lombok.Data;

import java.util.List;

@Data
public class AdvertRequest {
    private String location;
    private double price;
    private String description;
    private LodgingType type;
    private int numberOfPlaces;
    private List<String> images;
}