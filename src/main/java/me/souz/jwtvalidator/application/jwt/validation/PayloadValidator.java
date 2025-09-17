package me.souz.jwtvalidator.application.jwt.validation;

import me.souz.jwtvalidator.application.jwt.model.Payload;
import org.springframework.stereotype.Component;

@Component
public class PayloadValidator {
    private final NameValidator nameValidator;
    private final RoleValidator roleValidator;
    private final SeedValidator seedValidator;

    public PayloadValidator(NameValidator nameValidator,
                            RoleValidator roleValidator,
                            SeedValidator seedValidator) {
        this.nameValidator = nameValidator;
        this.roleValidator = roleValidator;
        this.seedValidator = seedValidator;
    }

    public boolean isValid(final Payload payload) {
        boolean validName = nameValidator.isValid(payload.getName());
        boolean validRole = roleValidator.isValid(payload.getRole());
        boolean validSeed = seedValidator.isValid(payload.getSeed());

        return validName && validRole && validSeed;
    }
}
