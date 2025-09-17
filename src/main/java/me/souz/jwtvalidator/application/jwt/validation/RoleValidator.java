package me.souz.jwtvalidator.application.jwt.validation;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoleValidator implements Validator {
    private static final Set<String> ALLOWED_ROLES = Set.of("Admin", "Member", "External");

    @Override
    public boolean isValid(final String value) {
        return ALLOWED_ROLES.contains(value);
    }
}
