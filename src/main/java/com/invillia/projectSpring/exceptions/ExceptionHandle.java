package com.invillia.projectSpring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound(HttpServletResponse response, Exception e) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
