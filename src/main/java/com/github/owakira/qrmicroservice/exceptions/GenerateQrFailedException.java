package com.github.owakira.qrmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenerateQrFailedException extends RuntimeException {
    public GenerateQrFailedException(String text) {
        super("Failed to generate qr. text=" + text);
    }
}
