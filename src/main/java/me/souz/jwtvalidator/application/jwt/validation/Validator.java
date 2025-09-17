package me.souz.jwtvalidator.application.jwt.validation;

public interface Validator {
    boolean isValid(final String value);
}
