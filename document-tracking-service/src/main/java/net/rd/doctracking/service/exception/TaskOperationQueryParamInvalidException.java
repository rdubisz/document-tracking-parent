package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.TaskOperationQueryParamModel;

public class TaskOperationQueryParamInvalidException extends RuntimeException {

    public TaskOperationQueryParamInvalidException(final TaskOperationQueryParamModel model) {
        super("Query-parameter invalid: " + model);
    }
}
