package de.tekup.tunirent.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private Long id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    @Size(min = 8)
    private String phone;
}
