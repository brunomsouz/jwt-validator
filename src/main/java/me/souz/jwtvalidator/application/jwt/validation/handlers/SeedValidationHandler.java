package me.souz.jwtvalidator.application.jwt.validation.handlers;

import me.souz.jwtvalidator.application.jwt.model.Payload;
import me.souz.jwtvalidator.application.jwt.validation.SeedValidator;

public class SeedValidationHandler extends ValidationHandler {
    private final SeedValidator seedValidator = new SeedValidator();

    @Override
    protected boolean handle(Payload payload) {
        return seedValidator.isValid(payload.getSeed());
    }
}
