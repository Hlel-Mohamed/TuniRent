package de.tekup.tunirent.dto;

import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDTO extends PostDTO{
    private String location;
    private double price;
    private String description;
    private LodgingType type;
    private int numberOfPlaces;
    private List<String> images;
}
