package me.souz.jwtvalidator.infrastructure.exception;

import lombok.Getter;
import me.souz.jwtvalidator.infrastructure.util.MessageFormatter;
import org.springframework.http.HttpStatus;

@Getter
public class AbstractException extends RuntimeException {
    private final HttpStatus httpStatus;

    public AbstractException(String messageKey, HttpStatus httpStatus) {
        super(MessageFormatter.getMessageForLocale(messageKey));
        this.httpStatus = httpStatus;
    }
}
