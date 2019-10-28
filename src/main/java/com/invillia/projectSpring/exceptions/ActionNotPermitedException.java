package com.invillia.projectSpring.exceptions;

public class ActionNotPermitedException extends RuntimeException {
    public ActionNotPermitedException(final String message){
        super("Ação não permitida!");
    }
}
