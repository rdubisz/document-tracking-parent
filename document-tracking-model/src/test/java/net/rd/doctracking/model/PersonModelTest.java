package net.rd.doctracking.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonModelTest {

    private final PersonModel tested = new PersonModel(
            999L, "xyz@bubble.com", "First", "Name", 321L,
            LocalDateTime.of(2023, 5, 1, 0, 0, 0));

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("999"));
        assertTrue(tested.toString().contains("xyz@bubble.com"));
        assertTrue(tested.toString().contains("First"));
        assertTrue(tested.toString().contains("Name"));
        assertTrue(tested.toString().contains("321"));
        assertTrue(tested.toString().contains("2023"));
    }
}
