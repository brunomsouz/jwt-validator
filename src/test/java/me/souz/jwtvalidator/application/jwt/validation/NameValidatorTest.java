package me.souz.jwtvalidator.application.jwt.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameValidatorTest {
    private NameValidator nameValidator;

    @BeforeEach
    void setup() {
        nameValidator = new NameValidator();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of("Fulano123", false),
                Arguments.of("Fulano de tal", true),
                Arguments.of("a".repeat(257), false),
                Arguments.of("a".repeat(256), true)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testIsValid(final String value, final boolean expected) {
        assertEquals(expected, nameValidator.isValid(value));
    }
}