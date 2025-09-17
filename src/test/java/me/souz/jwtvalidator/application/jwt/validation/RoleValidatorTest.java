package me.souz.jwtvalidator.application.jwt.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleValidatorTest {
    private RoleValidator roleValidator;

    @BeforeEach
    void setup() {
        roleValidator = new RoleValidator();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of("Admin", true),
                Arguments.of("Member", true),
                Arguments.of("External", true),
                Arguments.of("Moderator", false),
                Arguments.of("admin", false),
                Arguments.of(" Admin ", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testIsValid(final String value, final boolean expected) {
        assertEquals(expected, roleValidator.isValid(value));
    }
}