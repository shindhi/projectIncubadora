package com.invillia.projectSpring.exceptions;

public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(final String message){
        super("Time não encontrado, ID: "+ message);
    }
}
