package me.souz.jwtvalidator.application.jwt.validation.handlers;

import me.souz.jwtvalidator.application.jwt.model.Payload;

public abstract class ValidationHandler {
    protected ValidationHandler next;

    public ValidationHandler setNext(final ValidationHandler next) {
        this.next = next;
        return next;
    }

    public boolean validate(final Payload payload) {
        boolean currentValidation = handle(payload);

        if (!currentValidation) {
            return false;
        }

        return next == null || next.validate(payload);
    }

    protected abstract boolean handle(final Payload payload);
}
