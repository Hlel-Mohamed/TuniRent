package de.tekup.tunirent.utils;

import de.tekup.tunirent.enums.Role;
import de.tekup.tunirent.exception.UnauthorizedException;
import de.tekup.tunirent.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationUtils {

    public static List<Predicate> createPersonPredicates(Root<?> root, CriteriaBuilder cb, String email, String firstName, String lastName, String phone) {

        List<Predicate> predicates = new ArrayList<>();

        if (email != null && !email.isEmpty()) {
            predicates.add(cb.like(root.get("email"), "%" + email + "%"));
        }

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add(cb.like(root.get("firstName"), "%" + firstName + "%"));
        }

        if (lastName != null && !lastName.isEmpty()) {
            predicates.add(cb.like(root.get("lastName"), "%" + lastName + "%"));
        }

        if (phone != null && !phone.isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%" + phone + "%"));
        }

        return predicates;
    }

    public static Specification<User> createRoleBasedSpecification(Role currentUserRole) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Role.ROLE_ADMIN.equals(currentUserRole)) {
                // Admin can search for everyone, so we don't add any additional condition
            }
            else {
                throw new UnauthorizedException("Clients are not allowed to search for users");
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}