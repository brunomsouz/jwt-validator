package me.souz.jwtvalidator.application.jwt.validation;

import java.util.Set;

public class RoleValidator {
    private static final Set<String> ALLOWED_ROLES = Set.of("Admin", "Member", "External");

    public boolean isValid(final String value) {
        return ALLOWED_ROLES.contains(value);
    }
}
