package com.myaudiolibrary.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.management.InstanceAlreadyExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error", HttpStatus.NOT_FOUND);
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("Status", HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handleInstanceAlreadyExistsException(InstanceAlreadyExistsException e) {
        ModelAndView modelAndView = new ModelAndView("error", HttpStatus.CONFLICT);
        modelAndView.addObject("error", e.getMessage());
        modelAndView.addObject("Status", HttpStatus.CONFLICT);
        return modelAndView;
    }


}
