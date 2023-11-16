package de.tekup.tunirent.controller;

import de.tekup.tunirent.dto.AuthenticationResponse;
import de.tekup.tunirent.dto.PersonDTO;
import de.tekup.tunirent.dto.SignIn;
import de.tekup.tunirent.dto.SignUp;
import de.tekup.tunirent.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthControllerRegisterUserTest {

    @Mock
    private UserService userServiceMock;

    @Test
    public void shouldRegisterUserSuccessfully() {
        SignUp signUpDto = new SignUp();
        signUpDto.setFirstName("Aziz");
        signUpDto.setLastName("Hlel");
        signUpDto.setUsername("azizhlel");
        signUpDto.setEmail("azizhlel@example.com");
        signUpDto.setPassword("password");


        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setUsername("azizhlel");
        personDTO.setEmail("azizhlel@example.com");
        personDTO.setFirstName("Aziz");
        personDTO.setLastName("Hlel");

        when(userServiceMock.registerUser(signUpDto)).thenReturn(personDTO);

        AuthController authController = new AuthController(userServiceMock);
        ResponseEntity<PersonDTO> responseEntity = authController.registerUser(signUpDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(personDTO);
    }

    @Test
    public void shouldLoginUserSuccessfully() {
        SignIn signInDto = new SignIn();
        signInDto.setUsername("azizhlel");
        signInDto.setPassword("password");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setEmail("azizhlel@example.com");
        authenticationResponse.setToken("test_token");

        when(userServiceMock.authenticateUser(signInDto)).thenReturn(authenticationResponse);

        AuthController authController = new AuthController(userServiceMock);
        ResponseEntity<AuthenticationResponse> responseEntity = authController.login(signInDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(authenticationResponse);
    }
}
