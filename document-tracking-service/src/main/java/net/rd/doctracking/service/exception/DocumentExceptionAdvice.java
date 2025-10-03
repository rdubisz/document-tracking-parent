package net.rd.doctracking.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class DocumentExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(DocumentEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String documentNotFound(final DocumentEntityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DocumentInvalidException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String invalidDocumentInput(final DocumentInvalidException ex) {
        return ex.getMessage();
    }

//    @ResponseBody
//    @ExceptionHandler(PersonQueryParamInvalidException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    String invalidDocumentQueryInput(final PersonQueryParamInvalidException ex) {
//        return ex.getMessage();
//    }

}
