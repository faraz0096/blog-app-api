package com.faraz.blogappapis.exceptions;

import com.faraz.blogappapis.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {

        String message =ex.getMessage();
        APIResponse apiResponse = new APIResponse(message , false);
        return new ResponseEntity<APIResponse>(apiResponse , HttpStatus.NOT_FOUND);

    }
}
