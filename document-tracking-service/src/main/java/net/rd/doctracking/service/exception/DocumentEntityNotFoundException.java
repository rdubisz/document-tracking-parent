package net.rd.doctracking.service.exception;

public class DocumentEntityNotFoundException extends RuntimeException {

    public DocumentEntityNotFoundException(final Long id) {
        super("Document id(" + id + ") does not exist");
    }
}
