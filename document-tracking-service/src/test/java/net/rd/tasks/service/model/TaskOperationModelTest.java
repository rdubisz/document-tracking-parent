package net.rd.doctracking.service.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskOperationModelTest {

    private final TaskOperationModel tested = new TaskOperationModel(
            999L, 444L, 10000L, LocalDateTime.of(2023, 1, 1, 1, 1, 1));

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("999"));
        assertTrue(tested.toString().contains("444"));
        assertTrue(tested.toString().contains("2023"));
    }
}
