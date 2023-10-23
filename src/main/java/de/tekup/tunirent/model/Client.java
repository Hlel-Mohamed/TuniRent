package de.tekup.tunirent.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="client")
public class Client extends User{
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
}
