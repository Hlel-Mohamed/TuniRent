package de.tekup.tunirent.service;

import de.tekup.tunirent.enums.Role;
import de.tekup.tunirent.model.User;

import java.util.Collection;

public interface UserValidator {
    void validateUsernameAndEmailAvailability(String username, String email);
    void validateEmailAvailability(String email);
    void validatePassword(String encodedPassword, String providedPassword);
    void validateAdminPermissions(User user);
    void validateAdminPermissions();
    void validatePermissions(User user, Role role);
    void validatePermissions(User user, Collection<Role> allowedRoles);
    Long getCurrentLoggedInUserId();
}
