package me.souz.jwtvalidator.application.jwt.validation;

import me.souz.jwtvalidator.application.jwt.model.Payload;
import me.souz.jwtvalidator.application.jwt.validation.handlers.NameValidationHandler;
import me.souz.jwtvalidator.application.jwt.validation.handlers.RoleValidationHandler;
import me.souz.jwtvalidator.application.jwt.validation.handlers.SeedValidationHandler;
import me.souz.jwtvalidator.application.jwt.validation.handlers.ValidationHandler;
import org.springframework.stereotype.Component;

@Component
public class PayloadValidator {
    private final ValidationHandler chain;

    public PayloadValidator() {
        chain = new NameValidationHandler();
        chain.setNext(new RoleValidationHandler())
             .setNext(new SeedValidationHandler());
    }

    public boolean isValid(final Payload payload) {
        return chain.validate(payload);
    }
}
