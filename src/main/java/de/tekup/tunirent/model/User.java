package de.tekup.tunirent.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
}
