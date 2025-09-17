package me.souz.jwtvalidator.application.jwt.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtValidationRequestTest {
    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void testValidJwt() {
        JwtValidationRequest request = new JwtValidationRequest(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
                        + "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ."
                        + "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
        );

        Set<ConstraintViolation<JwtValidationRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testEmptyJwt() {
        JwtValidationRequest request = new JwtValidationRequest("");
        Set<ConstraintViolation<JwtValidationRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }

    @Test
    void testInvalidJwt() {
        JwtValidationRequest request = new JwtValidationRequest("!.@.#");
        Set<ConstraintViolation<JwtValidationRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
    }
}