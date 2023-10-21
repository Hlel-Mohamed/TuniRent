package de.tekup.tunirent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="advert")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name="description")
    private String description;
    @Column(name="type", nullable = false)
    private lodgingType type;
    @Column(name="number_of_places", nullable = false)
    private int numberOfPlaces;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "advert_id")
    private List<Image> images = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "client_id")
    private Client creator;
}
