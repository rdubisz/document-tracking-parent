package net.rd.doctracking.service.model;

import net.rd.doctracking.service.CommonUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentModelTest {

    private final DocumentModel tested = new DocumentModel(
            999L, "File-name", "Some words", CommonUtils.TS_1, "user1@dot.com", 321L);

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
