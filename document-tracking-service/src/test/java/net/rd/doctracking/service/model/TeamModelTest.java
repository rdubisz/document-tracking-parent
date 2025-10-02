package net.rd.doctracking.service.model;

import net.rd.doctracking.service.CommonUtils;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;


class TeamModelTest {

    private final TeamModel tested = new TeamModel(101L, "A-Team", CommonUtils.TS_1);

    @Test
    void testToString() {
        assertTrue(tested.toString().contains("101"));
        assertTrue(tested.toString().contains("A-Team"));
        assertTrue(tested.toString().contains("2023"));
    }
}
