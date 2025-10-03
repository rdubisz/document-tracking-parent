package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.PersonModel;

public class PersonInvalidException extends RuntimeException {

    public PersonInvalidException(final PersonModel model) {
        super("Person invalid: " + model);
    }
}
