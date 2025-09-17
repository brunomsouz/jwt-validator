package me.souz.jwtvalidator.application.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import me.souz.jwtvalidator.application.jwt.model.DecodedJwt;
import me.souz.jwtvalidator.application.jwt.model.Header;
import me.souz.jwtvalidator.application.jwt.model.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Log4j2
@Component
public class JwtDecoder {
    private final Base64.Decoder decoder = Base64.getUrlDecoder();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DecodedJwt decode(String encodedJwt) {
        final String[] parts = encodedJwt.split("\\.");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT format: must contain header, payload and signature");
        }

        final Header header = parse(parts[0], Header.class);
        final Payload payload = parse(parts[1], Payload.class);
        final byte[] signature = decoder.decode(parts[2]);

        return new DecodedJwt(header, payload, signature);
    }

    private <T> T parse(String encoded, Class<T> type) {
        String decodedString = new String(decoder.decode(encoded), StandardCharsets.UTF_8);

        try {
            return objectMapper.readValue(decodedString, type);
        } catch (JsonProcessingException ex) {
            log.error("Failed to parse JWT payload", ex);
            throw new IllegalArgumentException("Invalid JWT section: " + type.getSimpleName(), ex);
        }
    }
}
