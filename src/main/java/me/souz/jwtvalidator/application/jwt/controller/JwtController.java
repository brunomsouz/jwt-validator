package me.souz.jwtvalidator.application.jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import me.souz.jwtvalidator.application.jwt.dto.JwtValidationRequest;
import me.souz.jwtvalidator.application.jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class JwtController {
    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/jwt-validate")
    @Operation(summary = "Validates a JWT token")
    public ResponseEntity<String> validateJwt(@RequestBody @Valid final JwtValidationRequest jwtValidationRequest) {
        boolean result = jwtService.validateJwt(jwtValidationRequest.getJwt());

        return ResponseEntity.ok(result ? "verdadeiro" : "falso");
    }

}
