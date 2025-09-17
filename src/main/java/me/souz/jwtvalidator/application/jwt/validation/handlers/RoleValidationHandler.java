package me.souz.jwtvalidator.application.jwt.validation.handlers;

import me.souz.jwtvalidator.application.jwt.model.Payload;
import me.souz.jwtvalidator.application.jwt.validation.RoleValidator;

public class RoleValidationHandler extends ValidationHandler{
    private final RoleValidator roleValidator = new RoleValidator();

    @Override
    protected boolean handle(Payload payload) {
        return roleValidator.isValid(payload.getRole());
    }
}
