package de.tekup.tunirent.dto;

import de.tekup.tunirent.utils.ValidPassword;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUp {
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull @NotEmpty @NotBlank
    private String lastName;
    @NotNull @NotEmpty @NotBlank
    private String username;
    @NotNull @NotEmpty @NotBlank
    private String phone;
    @NotNull @NotEmpty @NotBlank @Email
    private String email;
    @NotNull @ValidPassword
    private String password;
    @Pattern(regexp = "^ROLE_[A-Z_]+$")
    private String role;
}
