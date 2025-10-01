package net.rd.doctracking.service.exception;

import net.rd.doctracking.service.model.TaskDefinitionModel;

public class TaskDefinitionInvalidException extends RuntimeException {

    public TaskDefinitionInvalidException(final TaskDefinitionModel model) {
        super("Task-definition invalid: " + model);
    }
}
