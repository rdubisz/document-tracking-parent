package net.rd.doctracking.service.exception;

public class TaskDefinitionEntityNotFoundException extends RuntimeException {

    public TaskDefinitionEntityNotFoundException(final Long id) {
        super("Task-definition id(" + id + ") does not exist");
    }
}
