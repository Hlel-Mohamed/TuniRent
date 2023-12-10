package de.tekup.tunirent.model;

import de.tekup.tunirent.enums.LodgingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Advert extends Post {
    @NotNull
    private String location;
    @NotNull
    private double price;
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private LodgingType type;
    @NotNull
    @Size(min = 1, max = 5)
    private int numberOfPlaces;
    @ElementCollection
    @CollectionTable(name = "advert_images", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "image")
    private List<String> images = new ArrayList<>(5);
}