package me.souz.jwtvalidator.application.jwt.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Payload {
    private final String role;
    private final String name;
    private final String seed;

    @JsonCreator
    public Payload(@JsonProperty("Role") String role,
                   @JsonProperty("Name") String name,
                   @JsonProperty("Seed") String seed) {
        this.role = role;
        this.seed = seed;
        this.name = name;
    }
}
