package me.souz.jwtvalidator.application.jwt.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeedValidatorTest {
    private SeedValidator seedValidator;

    @BeforeEach
    void setup() {
        seedValidator = new SeedValidator();
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("3", true),
                Arguments.of("5", true),
                Arguments.of("7", true),
                Arguments.of("13", true),
                Arguments.of("101", true),
                Arguments.of(null, false),
                Arguments.of("", false),
                Arguments.of(" ", false),
                Arguments.of("2", false),
                Arguments.of("4", false),
                Arguments.of("0", false),
                Arguments.of("-1", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testValid(final String value, final boolean expected) {
        assertEquals(expected, seedValidator.isValid(value));
    }
}