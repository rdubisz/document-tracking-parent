package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.TaskOperationModel;

public class TaskOperationInvalidException extends RuntimeException {

    public TaskOperationInvalidException(final TaskOperationModel model) {
        super("Task-operation invalid: " + model);
    }
}
