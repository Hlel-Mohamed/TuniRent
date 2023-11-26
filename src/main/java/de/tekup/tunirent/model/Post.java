package de.tekup.tunirent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name="post")
public abstract class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;
}
