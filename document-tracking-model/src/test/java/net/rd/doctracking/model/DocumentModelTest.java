package net.rd.doctracking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentModelTest {

    private final DocumentModel tested = new DocumentModel(
            999L, "File-name", "Some words",
            LocalDateTime.of(2023, 5, 1, 0, 0, 0),
            "user1@dot.com", 321L);

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("999"));
        assertTrue(tested.toString().contains("File-name"));
        assertTrue(tested.toString().contains("Some words"));
        assertTrue(tested.toString().contains("user1@dot.com"));
        assertTrue(tested.toString().contains("321"));
        assertTrue(tested.toString().contains("2023"));
    }
}
