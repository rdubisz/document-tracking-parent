package net.rd.doctracking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PersonExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(PersonEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFound(final PersonEntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PersonInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidPersonInput(final PersonInvalidException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PersonQueryParamInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidPersonQueryInput(final PersonQueryParamInvalidException ex) {
        return ex.getMessage();
    }

}
