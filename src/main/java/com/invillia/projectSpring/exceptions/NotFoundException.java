package com.invillia.projectSpring.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message){
        super("Time não encontrado, ID: "+ message);
    }
}
