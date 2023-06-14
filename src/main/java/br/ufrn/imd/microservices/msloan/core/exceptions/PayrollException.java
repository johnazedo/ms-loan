package br.ufrn.imd.microservices.msloan.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class PayrollException extends RuntimeException {
    public PayrollException(String message) {
        super(message);
    }
}
