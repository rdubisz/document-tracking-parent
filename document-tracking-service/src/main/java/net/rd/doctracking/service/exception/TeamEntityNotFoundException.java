package net.rd.doctracking.service.exception;

public class TeamEntityNotFoundException extends RuntimeException {

    public TeamEntityNotFoundException(final Long id) {
        super("Team id(" + id + ") does not exist");
    }
}
