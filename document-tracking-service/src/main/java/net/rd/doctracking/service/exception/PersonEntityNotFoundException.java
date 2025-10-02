package net.rd.doctracking.service.exception;

public class PersonEntityNotFoundException extends RuntimeException {

    public PersonEntityNotFoundException(final Long id) {
        super("Person id(" + id + ") does not exist");
    }
}
