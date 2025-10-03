package net.rd.doctracking.service.exception;


import net.rd.doctracking.service.model.DocumentModel;

public class DocumentInvalidException extends RuntimeException {

    public DocumentInvalidException(final DocumentModel model) {
        super("Document invalid: " + model);
    }
}
