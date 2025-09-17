package me.souz.jwtvalidator.application.jwt.validation;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NameValidator implements Validator {
    private static final int NAME_MAX_LENGTH = 256;
    private static final Pattern NAME_PATTERN = Pattern.compile("^\\D*$");

    @Override
    public boolean isValid(final String value) {
        if (value == null || value.isBlank()) {
            return false;
        }

        if (value.length() > NAME_MAX_LENGTH) {
            return false;
        }

        return NAME_PATTERN.matcher(value).matches();
    }
}
