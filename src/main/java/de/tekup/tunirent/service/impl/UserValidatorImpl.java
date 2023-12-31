package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.enums.Role;
import de.tekup.tunirent.exception.AccessDeniedException;
import de.tekup.tunirent.exception.EmailTakenException;
import de.tekup.tunirent.exception.InvalidPasswordException;
import de.tekup.tunirent.exception.UsernameTakenException;
import de.tekup.tunirent.exception.UserNotFoundException;
import de.tekup.tunirent.model.User;
import de.tekup.tunirent.repository.UserRepository;
import de.tekup.tunirent.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserValidatorImpl implements UserValidator {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found!";

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void validateUsernameAndEmailAvailability(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UsernameTakenException("Username is already taken!");
        }

        validateEmailAvailability(email);
    }

    @Override
    public void validateEmailAvailability(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailTakenException("Email is already taken!");
        }
    }

    @Override
    public void validatePassword(String encodedPassword, String providedPassword) {
        if (!passwordEncoder.matches(providedPassword, encodedPassword)) {
            throw new InvalidPasswordException("Incorrect password!");
        }
    }
    @Override
    public void validateAdminPermissions(User user) {
        if (!user.getRole().equals(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("Unauthorized access. This operation is restricted to administrators.");
        }
    }

    @Override
    public void validateAdminPermissions() {
        validateAdminPermissions(getCurrentLoggedInUser());
    }


    @Override
    public void validatePermissions(User user, Role role) {
        validatePermissions(user, List.of(role));
    }

    @Override
    public void validatePermissions(User user, Collection<Role> allowedRoles) {
        Role userRole = user.getRole();

        if (!allowedRoles.contains(userRole)) {
            String allowedRolesString = allowedRoles.stream()
                    .map(Role::name)
                    .reduce((role1, role2) -> role1 + " or " + role2)
                    .orElse("");

            throw new AccessDeniedException("Unauthorized access. This operation is restricted to " + allowedRolesString + " only.");
        }
    }

    private User getCurrentLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE));
    }
    @Override
    public Long getCurrentLoggedInUserId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE)).getId();
    }

}
