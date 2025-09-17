package me.souz.jwtvalidator.application.jwt.validation;

import org.springframework.stereotype.Component;

@Component
public class SeedValidator implements Validator {
    @Override
    public boolean isValid(final String value) {
        final int parsedInt;

        try {
            parsedInt = Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            return false;
        }

        if (parsedInt <= 1) {
            return false;
        }

        if (parsedInt % 2 == 0) {
            return false;
        }

        int sqrt = (int) Math.sqrt(parsedInt);

        for (int i = 3; i <= sqrt; i+= 2) {
            if (parsedInt % i == 0) {
                return false;
            }
        }

        return true;
    }
}
