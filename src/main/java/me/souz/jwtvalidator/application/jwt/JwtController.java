package me.souz.jwtvalidator.application.jwt;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import me.souz.jwtvalidator.application.jwt.model.JwtValidationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class JwtController {

    @PostMapping("/jwt-validate")
    @Operation(summary = "Validates a JWT token")
    public ResponseEntity<String> validateJwt(@RequestBody @Valid JwtValidationRequest jwtValidationRequest) {
        log.info("Received JWT token for validation:  {}", jwtValidationRequest.getJwt());
        return ResponseEntity.ok().build();
    }

}
