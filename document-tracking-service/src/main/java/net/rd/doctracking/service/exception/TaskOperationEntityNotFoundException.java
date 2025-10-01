package net.rd.doctracking.service.exception;

public class TaskOperationEntityNotFoundException extends RuntimeException {

    public TaskOperationEntityNotFoundException(final Long id) {
        super("Task-operation id(" + id + ") does not exist");
    }
}
