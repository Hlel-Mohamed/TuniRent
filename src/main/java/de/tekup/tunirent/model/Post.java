package de.tekup.tunirent.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="post")
public abstract class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "client_id")
    private Client creator;
}
