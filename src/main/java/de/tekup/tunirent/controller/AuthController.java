package de.tekup.tunirent.controller;

import de.tekup.tunirent.dto.AuthenticationResponse;
import de.tekup.tunirent.dto.PersonDTO;
import de.tekup.tunirent.dto.SignIn;
import de.tekup.tunirent.dto.SignUp;
import de.tekup.tunirent.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<PersonDTO> registerUser(@RequestBody @Valid SignUp signUpDto){

        PersonDTO res;
        res = userService.registerUser(signUpDto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid SignIn loginDto) {

        AuthenticationResponse res;
        res = userService.authenticateUser(loginDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
