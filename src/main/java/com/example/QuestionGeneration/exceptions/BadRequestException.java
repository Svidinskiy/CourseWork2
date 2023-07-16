package com.example.QuestionGeneration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Not enough questions available");
    }

}