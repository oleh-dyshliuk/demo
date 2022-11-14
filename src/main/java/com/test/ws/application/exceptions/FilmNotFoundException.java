package com.test.ws.application.exceptions;

public class FilmNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Cannot find film with id: %d";

    public FilmNotFoundException(Long id){
        super(String.format(ERROR_MESSAGE, id));
    }
}
