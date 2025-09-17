package me.souz.jwtvalidator.application.jwt.service;

import me.souz.jwtvalidator.application.jwt.decoder.JwtDecoder;
import me.souz.jwtvalidator.application.jwt.model.DecodedJwt;
import me.souz.jwtvalidator.application.jwt.model.Header;
import me.souz.jwtvalidator.application.jwt.model.Payload;
import me.souz.jwtvalidator.application.jwt.validation.PayloadValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {
    @Mock
    private JwtDecoder jwtDecoder;
    @Mock
    private PayloadValidator payloadValidator;

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService(jwtDecoder, payloadValidator);
    }

    @Test
    void testValidJwt() {
        String jwtString = "valid.jwt.token";
        DecodedJwt decodedJwt = new DecodedJwt(new Header(""), new Payload("", "", ""), new byte[0]);

        when(jwtDecoder.decode(jwtString)).thenReturn(decodedJwt);
        when(payloadValidator.isValid(any())).thenReturn(true);

        boolean result = jwtService.validateJwt(jwtString);

        assertTrue(result);

        verify(jwtDecoder).decode(anyString());
        verify(payloadValidator).isValid(any());
    }

    @Test
    void testInvalidJwtFormat() {
        String jwtString = "invalid.jwt";

        when(jwtDecoder.decode(jwtString)).thenThrow(new IllegalArgumentException("Invalid JWT format"));

        boolean result = jwtService.validateJwt(jwtString);

        assertFalse(result);

        verify(jwtDecoder).decode(jwtString);
        verifyNoInteractions(payloadValidator);
    }

    @Test
    void testInvalidPayload() {
        String jwtString = "valid.jwt.token";
        DecodedJwt decodedJwt = new DecodedJwt(new Header(""), new Payload("", "", ""), new byte[0]);

        when(jwtDecoder.decode(jwtString)).thenReturn(decodedJwt);
        when(payloadValidator.isValid(any())).thenReturn(false);

        boolean result = jwtService.validateJwt(jwtString);

        assertFalse(result);

        verify(jwtDecoder).decode(anyString());
        verify(payloadValidator).isValid(any());
    }
}