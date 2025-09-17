package me.souz.jwtvalidator.application.jwt.validation.handlers;

import me.souz.jwtvalidator.application.jwt.model.Payload;
import me.souz.jwtvalidator.application.jwt.validation.NameValidator;

public class NameValidationHandler extends ValidationHandler{
    private final NameValidator nameValidator = new NameValidator();

    @Override
    protected boolean handle(Payload payload) {
        return nameValidator.isValid(payload.getName());
    }
}
