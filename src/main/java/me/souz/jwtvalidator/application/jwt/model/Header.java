package me.souz.jwtvalidator.application.jwt.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Header {
    private final String alg;

    @JsonCreator
    public Header(@JsonProperty("alg") String alg) {
        this.alg = alg;
    }
}
