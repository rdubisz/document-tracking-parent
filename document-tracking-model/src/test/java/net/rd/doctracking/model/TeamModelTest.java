package net.rd.doctracking.model;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


class TeamModelTest {

    private final TeamModel tested = new TeamModel(101L, "A-Team",
            LocalDateTime.of(2023, 5, 1, 0, 0, 0));

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("101"));
        assertTrue(tested.toString().contains("A-Team"));
        assertTrue(tested.toString().contains("2023"));
    }
}
