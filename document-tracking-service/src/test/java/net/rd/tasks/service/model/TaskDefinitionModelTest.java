package net.rd.doctracking.service.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;


class TaskDefinitionModelTest {

    private final TaskDefinitionModel tested = new TaskDefinitionModel(
            100L, "Task1", "t1");

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("Task1"));
        assertTrue(tested.toString().contains("t1"));
    }
}
