package me.souz.jwtvalidator.application.jwt.service;

import lombok.extern.log4j.Log4j2;
import me.souz.jwtvalidator.application.jwt.decoder.JwtDecoder;
import me.souz.jwtvalidator.application.jwt.model.DecodedJwt;
import me.souz.jwtvalidator.application.jwt.validation.PayloadValidator;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JwtService {
    private final JwtDecoder jwtDecoder;
    private final PayloadValidator payloadValidator;

    public JwtService(JwtDecoder jwtDecoder, PayloadValidator payloadValidator) {
        this.jwtDecoder = jwtDecoder;
        this.payloadValidator = payloadValidator;
    }

    public boolean validateJwt(final String jwtString) {
        final DecodedJwt decodedJwt;

        try {
            decodedJwt = jwtDecoder.decode(jwtString);
        } catch (IllegalArgumentException ex) {
            log.error("Invalid JWT String: {}", jwtString);
            return false;
        }

        return payloadValidator.isValid(decodedJwt.getPayload());
    }
}
