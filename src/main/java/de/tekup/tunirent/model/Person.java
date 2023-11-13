package de.tekup.tunirent.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @Transient
    private List<Post> posts;

}