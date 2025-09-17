package me.souz.jwtvalidator.application.jwt.decoder;

import me.souz.jwtvalidator.application.jwt.model.DecodedJwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class JwtDecoderTest {
    private static JwtDecoder jwtDecoder;

    @BeforeEach
    void setup() {
        jwtDecoder = new JwtDecoder();
    }

    @Test
    void testValidJwtDecoding() {
        String headerJson = "{\"alg\":\"HS256\"}";
        String payloadJson = "{\"Role\":\"Admin\",\"Name\":\"Fulano de Tal\",\"Seed\":3}";

        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(headerJson.getBytes());
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payloadJson.getBytes());
        String signature = Base64.getUrlEncoder().withoutPadding().encodeToString("signature".getBytes());

        String jwt = String.join(".", encodedHeader, encodedPayload, signature);

        DecodedJwt decodedJwt = jwtDecoder.decode(jwt);

        assertEquals("HS256", decodedJwt.getHeader().getAlg());
        assertEquals("Admin", decodedJwt.getPayload().getRole());
        assertEquals("Fulano de Tal", decodedJwt.getPayload().getName());
        assertEquals("3", decodedJwt.getPayload().getSeed());
    }

    @Test
    void testInvalidJwtFormat() {
        String invalidJwt = "abc.def";

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jwtDecoder.decode(invalidJwt));

        assertEquals("Invalid JWT format: must contain header, payload and signature", ex.getMessage());
    }

    @Test
    void testInvalidPayloadJson() {
        String invalidPayload = Base64.getUrlEncoder().withoutPadding().encodeToString("not-json".getBytes());
        String validHeader = Base64.getUrlEncoder().withoutPadding().encodeToString("{\"alg\":\"HS256\"}".getBytes());
        String signature = Base64.getUrlEncoder().withoutPadding().encodeToString("Name".getBytes());

        String jwt = String.join(".", validHeader, invalidPayload, signature);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> jwtDecoder.decode(jwt));

        assertTrue(ex.getMessage().contains("Invalid JWT section: Payload"));
    }
}