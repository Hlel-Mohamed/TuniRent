package de.tekup.tunirent.model;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fileName", nullable = false)
    private String fileName;
    @Column(name = "contentType", nullable = false)
    private String contentType;
    @Lob
    private byte[] data;
}