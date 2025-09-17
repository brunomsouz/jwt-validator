package me.souz.jwtvalidator.application.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DecodedJwt {
    private final Header header;
    private final Payload payload;
    private final byte[] signature;
}
