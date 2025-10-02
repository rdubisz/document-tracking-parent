package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.PersonQueryParamModel;

public class PersonQueryParamInvalidException extends RuntimeException {

    public PersonQueryParamInvalidException(final PersonQueryParamModel model) {
        super("Query-parameter invalid: " + model);
    }
}
