package net.rd.doctracking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TaskExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(TaskDefinitionEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String taskDefinitionEntityNotFound(final TaskDefinitionEntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TaskDefinitionInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidTaskDefinitionInput(final TaskDefinitionInvalidException ex) {
        return ex.getMessage();
    }
}
