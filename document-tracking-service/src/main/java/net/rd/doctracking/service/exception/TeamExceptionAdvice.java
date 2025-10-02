package net.rd.doctracking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class TeamExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(TeamEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String teamEntityNotFound(final TeamEntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TeamInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidTeamInput(final TeamInvalidException ex) {
        return ex.getMessage();
    }
}
