package de.tekup.tunirent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}