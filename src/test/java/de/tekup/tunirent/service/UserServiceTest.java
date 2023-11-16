package de.tekup.tunirent.service;

import de.tekup.tunirent.dto.PersonDTO;
import de.tekup.tunirent.dto.SignUp;
import de.tekup.tunirent.mapper.UserMapper;
import de.tekup.tunirent.model.User;
import de.tekup.tunirent.repository.UserRepository;
import de.tekup.tunirent.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userMapper = mock(UserMapper.class);
        userService.setUserMapper(userMapper);

    }

    @Test
    public void testRegisterUser() {
        SignUp signUpDto = new SignUp();
        signUpDto.setFirstName("Aziz");
        signUpDto.setLastName("Hlel");
        signUpDto.setUsername("azizhlel");
        signUpDto.setEmail("azizhlel@example.com");
        signUpDto.setRole("ROLE_ADMIN");
        signUpDto.setPassword(passwordEncoder.encode("password"));

        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        when(userMapper.convertToEntity(any())).thenCallRealMethod();
        when(userMapper.convertToDTO(any())).thenCallRealMethod();

        PersonDTO result = userService.registerUser(signUpDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Aziz", result.getFirstName());
        assertEquals("Hlel", result.getLastName());
        assertEquals("azizhlel", result.getUsername());
        assertEquals("azizhlel@example.com", result.getEmail());

        verify(userRepository, times(1)).save(any());
    }
}
