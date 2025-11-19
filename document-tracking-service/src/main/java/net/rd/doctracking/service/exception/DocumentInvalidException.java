package net.rd.doctracking.service.exception;


import net.rd.doctracking.model.DocumentModel;

public class DocumentInvalidException extends RuntimeException {

    public DocumentInvalidException(final DocumentModel model) {
        super("Document invalid: " + model);
    }
}
