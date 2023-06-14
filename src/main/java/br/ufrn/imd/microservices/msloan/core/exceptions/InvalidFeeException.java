package br.ufrn.imd.microservices.msloan.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFeeException extends RuntimeException {
    public InvalidFeeException(String message) {
        super(message);
    }
}
